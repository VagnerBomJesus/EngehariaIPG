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

def Menu(Titulo, Opcoes, np):
    print("\t\t\t\t\t\t\t\t\033[1;33;m---------------------------\033[m")
    print("\t\t\t\t\t\t\t\t\033[1;32;mUsado o Algoritmo de Disktra\033[m")
    print("\t\t\t\t\t\t\t\t\033[1;33;m---------------------------\033[m")
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

def Caminho():
    from Djikstra.Djikstra import LerCidadesParaVetor





def MenuPrincipal():

    print("\n")
    Titulo = "Menu Principal"
    Opcoes = ["Caminho Entre Paises","Ubunto Solutionr"]
    np = 1
    while True:
        op = Menu(Titulo, Opcoes, np)
        if   op == 1:
            Caminho()
        elif op == 0:
            break
MenuPrincipal()