CREATE OR REPLACE PACKAGE bda IS
    FUNCTION to_unix_timestamp (
        pi_date DATE
    ) RETURN NUMBER;

    FUNCTION from_unix_timestamp (
        pi_unix_timestamp NUMBER
    ) RETURN DATE;

    PROCEDURE get_page (
        url      IN VARCHAR2,
        username IN VARCHAR2 DEFAULT NULL,
        password IN VARCHAR2 DEFAULT NULL,
        realm    IN VARCHAR2 DEFAULT NULL
    );

    FUNCTION return_page (
        url      IN VARCHAR2,
        username IN VARCHAR2 DEFAULT NULL,
        password IN VARCHAR2 DEFAULT NULL,
        realm    IN VARCHAR2 DEFAULT NULL
    ) RETURN CLOB;

END bda;
/

/***************************/


create or replace PACKAGE BODY bda IS

    PROCEDURE set_wallet IS
    BEGIN
--configurar o Oracle Wallet na sessão
        utl_http.set_wallet('file:/u01/app/oracle/product/12.1.0/dbhome_1/owm/wallets/oracle', 'WalletPassword!');
    END set_wallet;

    FUNCTION to_unix_timestamp (
        pi_date DATE
    ) RETURN NUMBER IS
        v_unix_timestamp NUMBER;
    BEGIN
        v_unix_timestamp := trunc((pi_date - TO_DATE('01/01/1970', 'dd/mm/yyyy')) * 86400);
-- 86400 = 24*60*60
        IF ( v_unix_timestamp < 0 ) THEN
            raise_application_error(-20000, 'unix_timestamp:: unix_timestamp cannot benagative');
        END IF;
        RETURN v_unix_timestamp;
    END to_unix_timestamp;
    
    FUNCTION from_unix_timestamp (  pi_unix_timestamp NUMBER ) RETURN DATE IS  v_date DATE;
    
BEGIN v_date := pi_unix_timestamp / 86400 + TO_DATE('01/01/1970', 'dd/mm/yyyy'); -- 86400 = 24*60*60
    return v_date;
    end from_unix_timestamp;
PROCEDURE get_web_page (
    url      IN VARCHAR2,
    username IN VARCHAR2 DEFAULT NULL,
    password IN VARCHAR2 DEFAULT NULL,
    realm    IN VARCHAR2 DEFAULT NULL
)
    as
--based on https://docs.oracle.com/database/121/ARPLS/u_http.htm and https://oraclebase.com/articles/misc/utl_http-and-ssl
     req utl_http.req;
    resp utl_http.resp;
    my_scheme VARCHAR2(256);
    my_realm VARCHAR2(256);
    name VARCHAR2(256);
    value VARCHAR2(16384);
    text VARCHAR2(32767);
    
    BEGIN
-- Turn off checking of status code. We will check it by ourselves.
utl_http.set_response_error_check(false);

req := utl_http.begin_request(url);

IF ( username IS NOT NULL ) THEN
    utl_http.set_authentication(req, username, password); -- Use HTTP Basic Authen.Scheme
END IF;

resp := utl_http.get_response(req); IF ( resp.status_code = utl_http.http_unauthorized ) THEN
    utl_http.get_authentication(resp, my_scheme, my_realm, false);
    dbms_output.put_line('Web proxy server is protected.');
    dbms_output.put('Please supplied the required '
                    || my_scheme
                    || ' authentication username/password for realm '
                    || my_realm
                    || ' for the proxy server.');

    utl_http.end_response(resp);
    RETURN;
ELSIF ( resp.status_code = utl_http.http_proxy_auth_required ) THEN
    utl_http.get_authentication(resp, my_scheme, my_realm, true);

dbms_output.put_line('Web page '
                     || url
                     || ' is protected.');

dbms_output.put('Please supplied the required '
                || my_scheme
                || ' authentication username/password for realm '
                || my_realm
                || ' for the Web page.');

utl_http.end_response(resp);

RETURN;
END
if;

FOR i IN 1..utl_http.get_header_count(resp) LOOP
    utl_http.get_header(resp, i, name, value);
    dbms_output.put_line(name
                         || ': '
                         || value);
END LOOP;
-- Loop through the response.
BEGIN
dbms_output.put_line(''); loop
    utl_http.read_text(resp, text, 32766);
    dbms_output.put_line(text);
END LOOP;

exception
WHEN utl_http.end_of_body THEN
utl_http.end_response(resp);
END; EXCEPTION
    WHEN OTHERS THEN
        utl_http.end_response(resp);
        RAISE;
        utl_http.end_response(resp);
END get_web_page;
FUNCTION return_web_page (
    url      IN VARCHAR2,
    username IN VARCHAR2 DEFAULT NULL,
    password IN VARCHAR2 DEFAULT NULL,
    realm    IN VARCHAR2 DEFAULT NULL
) RETURN CLOB
    as
--based on https://docs.oracle.com/database/121/ARPLS/u_http.htm and https://oraclebase.com/articles/misc/utl_http-and-ssl
     req utl_http.req;
    resp utl_http.resp;
    my_scheme VARCHAR2(256);
    
    my_realm VARCHAR2(256);
name VARCHAR2(256);
value VARCHAR2(16384);
text VARCHAR2(32767);
result CLOB;
BEGIN
-- Turn off checking of status code. We will check it by ourselves.
utl_http.set_response_error_check(false);
req := utl_http.begin_request(url);
IF ( username IS NOT NULL ) THEN
utl_http.set_authentication(req, username, password); -- Use HTTP Basic Authen.Scheme
END IF;
resp := utl_http.get_response(req);
IF ( resp.status_code = utl_http.http_unauthorized ) THEN
    utl_http.get_authentication(resp, my_scheme, my_realm, false);
    dbms_output.put_line('Web proxy server is protected.');
    dbms_output.put('Please supplied the required '
                    || my_scheme
                    || ' authentication username/password for realm '
                    || my_realm
                    || ' for the proxy server.');
                    utl_http.end_response(resp);
return(result); elsif(resp.status_code = utl_http.http_proxy_auth_required) THEN
utl_http.get_authentication(resp, my_scheme, my_realm, true);

dbms_output.put_line('Web page '
                     || url
                     || ' is protected.');
dbms_output.put('Please supplied the required '
                || my_scheme
                || ' authentication username/password for realm '
                || my_realm
                || ' for the Web page.');

utl_http.end_response(resp);

RETURN ( result );
END IF;
-- Loop through the response.
BEGIN
    LOOP
        utl_http.read_text(resp, text, 32766);
        result := result || text;
    END LOOP;

exception
WHEN utl_http.end_of_body THEN
utl_http.end_response(resp);
return(result);
END; EXCEPTION
    WHEN OTHERS THEN
        utl_http.end_response(resp);
        RAISE;
        utl_http.end_response(resp);
END return_web_page;
END bda;

/****************************/

CREATE OR REPLACE PACKAGE test_bda IS
    PROCEDURE test_unix_timestamp;

    PROCEDURE test_openweathermap;

    PROCEDURE test_darksky;

END test_bda;
/

create or replace PACKAGE BODY test_bda IS

    PROCEDURE test_unix_timestamp IS
        v_unix_timestamp NUMBER(10);
        v_date_char      VARCHAR2(20);
    BEGIN
        SELECT
            bda.to_unix_timestamp(sysdate)
        INTO v_unix_timestamp
        FROM
            dual;

        dbms_output.put_line(v_unix_timestamp);
        SELECT
            to_char(bda.from_unix_timestamp(1551691827), 'dd/mm/yyyy hh24:mi:ss')
        INTO v_date_char
        FROM
            dual;

dbms_output.put_line(v_date_char);
    END test_unix_timestamp;
    PROCEDURE test_openweathermap IS
v_json CLOB;
v_json_query CLOB;
v_json_value CLOB;
BEGIN
v_json := bda.return_web_page('api.openweathermap.org/data/2.5/weather?q=Guarda,pt'
                              || '&'
                              || 'APPID=d103b
79e27bc64c2d4d6cc7c50c4b714');
--dbms_output.put_line(v_json);
SELECT
    JSON_QUERY(v_json, '$.coord')
INTO v_json_query
FROM
    dual;

dbms_output.put_line(v_json_query);

SELECT
    JSON_VALUE(v_json_query, '$.lon')
INTO v_json_value
FROM
    dual;

dbms_output.put_line(v_json_value);

SELECT
    JSON_VALUE(v_json_query, '$.lon')
INTO v_json_value
FROM
    dual;

dbms_output.put_line(v_json_value);

SELECT
    JSON_QUERY(v_json, '$.weather')
INTO v_json_query
FROM
    dual;

dbms_output.put_line(v_json_query);

SELECT
    JSON_VALUE(v_json_query, '$.id')
INTO v_json_value
FROM
    dual;

dbms_output.put_line(v_json_value);
END test_openweathermap;


END test_bda;

set serveroutput on;
--Test bda package
--Current Unix Timestamp
exec test_bda.test_unix_timestamp;