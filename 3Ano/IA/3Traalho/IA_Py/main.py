#--------------------Validaçãoes----------------------
from tkinter import PhotoImage
def ValidaOpcoes(Opcoes):
    import re
    regex = r"[0-7]{1,1}"
    matches = re.finditer(regex, Opcoes, re.MULTILINE)
    matchNum = 0
    for matchNum, match in enumerate(matches):
        matchNum = matchNum + 1
    if matchNum == 1:
        if len(Opcoes)==1:
            return True
        else:
            return False
    else:
        return False
def ValidaCountry(Country):
    import re
    regex = r"[A-Z][a-z]{2,8}( [a-z]{2,3}){0,1}( [A-Z][a-z]{2,8}){0,4}"
    matches = re.finditer(regex, Country, re.MULTILINE)
    matchNum = 0
    for matchNum, match in enumerate(matches):
        matchNum = matchNum + 1
    if matchNum == 1:
        return True
    else:
        return False

#-------------------------------------------------------------
def Menu(Titulo, Opcoes, np):
    print("\t\t\t\t\t\t\t\t\033[1;33;m---------------------------\033[m")
    print("\t\t\t\t\t\t\t\t\033[1;32;mUsado o Algoritmo de Disktra\033[m")
    print("\t\t\t\t\t\t\t\t\033[1;33;m---------------------------\033[m")
    from datetime import datetime
    agora = datetime.now()
    #print(agora)
    print("\033[1;30;mData: ", agora.strftime("%Y-%m-%d\033[m"))
    print("\033[1;30;mHora: ", agora.strftime("%X\033[m"))
    print("\n")
    print(Titulo)
    print()
    for i in range(np):
        print(i + 1, "- ", Opcoes[i])
    print("0 -  Terminar")
    while True:
        print("Opção?")

        op = int(input())
        if (op >= 0 and op <= np):
            break
    return op

#----------------GestaoPaises------------------
def GestaoPaises():

    Titulo = "Gestão de Paises"
    Opcoes = ["Caminho entre Paises"]
    np = 3
    while True:
        op = Menu(Titulo, Opcoes, np)
        if op == 1:
            Caminho()
        elif op == 0:
            break

def PesquusPaisVetor(paises,pais):
    for p in paises:
        if p[0]==pais:
            return p
def PesquusPaisNome(paises,pais):
    for p in paises:
        if p[1]==pais:
            return p
def Caminho():
    from Djikstra.Djikstra import LerPaisesParaVetor
    paises = LerPaisesParaVetor()
    from Djikstra.Djikstra import Graph
    graph = Graph()
    for i in range(1, len(paises)):
        p = paises[i]
        pais = p[0]
        vizinhos_string = p[2]
        # print(pais, vizinhos)
        if vizinhos_string == "":
            continue
        vizinhos = vizinhos_string.split(',')
        for v in vizinhos:
            # print(pais, v)
            graph.add_edge(pais, v, 1)
    from Djikstra.Djikstra import dijsktra
    #caminho = dijsktra(graph, 'DE', 'PT');
    print("Exemplo de paises")
    for i in range(1, len(paises)):
        print(paises[i][0], " - ", paises[i][1])
    while True:
        origem = input("Pais de Origem?")
        if ValidaCountry(origem) == True:
            break
    while True:
        destino = input("Pais de Destino?")
        if ValidaCountry(destino)== True:
            break
    iso_origem = PesquusPaisNome(paises, origem)[0]
    iso_destino = PesquusPaisNome(paises, destino)[0]
    caminho = dijsktra(graph, iso_origem, iso_destino)
    x=""
    for iso in caminho:
        pais=PesquusPaisVetor(paises,iso)
        x=x+pais[1] + " -> "
    print(origem+"->"+ destino + " : " + x.rstrip(" -> "))
    #print()
    #print(caminho)
    input("Prima enter para continuar")


def LerData(msg, min=None, max=None):
    from datetime import datetime
    while True:
        data_texto = input(msg)
        try:
            data = datetime.strptime(data_texto, '%Y-%m-%d')
        except ValueError:
            print("Data inválida")
            continue
        if data >= min and data <= max:
            break
        else:
            print("Data fora do intervalo %s e %s"
                  % (min.strftime("%Y-%m-%d"),
                     max.strftime("%Y-%m-%d")))
    return data_texto

from datetime import datetime
data_min = datetime.strptime("1000-01-01", '%Y-%m-%d')
data_max = datetime.strptime("2019-12-31", '%Y-%m-%d')


def Solution():
    print("\n")

    print("\033[1;34;m\033[m")

    print("Com base num conjunto de dados sobre: "
          "\nContinentes, Países, Códigos Postais, "
          "Cidades entre outros elaborar algoritmos e respetivo programa."
          "\nNeste trabalho será inventar um algoritmo que permita determinar "
          "caminhos entre dois países."
          "\nExemplos: Portugal -> Alemanha: Portugal -> Espanha -> França -> Alemanha")
    input("Prima enter para continuar")
def MenuPrincipal():

    print("\n")
    Titulo = "Menu Principal"
    Opcoes = ["Caminho Entre Paises","Ubunto Solutionr"]
    np = 2
    while True:
        op = Menu(Titulo, Opcoes, np)
        if   op == 1:
            Caminho()
        elif op == 2:
            Solution()
        elif op == 0:
            break
MenuPrincipal()

