import logging
import os
import platform
import subprocess
import sys
import time
import webbrowser
from pathlib import Path

from web3 import Web3
from web3.middleware import geth_poa_middleware


def a():
    # Exécution du script Python avec des privilèges d'administrateur
    os.system('start cmd /k "python etheriumAccounts.py"')


def clear():
    linux = 'clear'
    windows = 'cls'
    os.system([linux, windows][os.name == 'nt'])


logging.disable(sys.maxsize)
number = 1
data = ""
current_dir = os.getcwd()

three_dirs_up = os.path.abspath(os.path.join(os.getcwd(), "../../../"))
jar_path = os.path.join(three_dirs_up, "target", "camel-sprint-boot-service-a-0.0.1-SNAPSHOT.jar")

if not Path(jar_path).exists():
    # Rechercher le fichier pom.xml dans le répertoire parent
    pom_dir = os.path.abspath('../../../')
    for root, dirs, files in os.walk(pom_dir):
        if 'pom.xml' in files:
            pom_dir = os.path.abspath(root)

            break
        else:
            print("Le fichier pom.xml n'a pas été trouvé dans le répertoire actuel et ses sous-répertoires.")
            exit()

    # Lancer la commande "mvn clean install" dans le répertoire contenant le pom.xml
    subprocess.run(["mvn", "clean", "install"], cwd=pom_dir)
else:
    print(f"Le fichier {jar_path} existe.")

while number != '0':
    print()
    data += ' ----------------------------\n'
    if os.name == "nt":
        data = (' ----------------------------\n')
        data += ' Hi ' + platform.uname()[1] + '\n'
    data += ' ----------------------------\n'
    data += ' Select option:\n'
    data += ' [1] Install & Activate ActiveMQ\n'
    data += ' [2] Launch ActiveMQ\n'
    data += ' [3] Voltaic data entry\n'
    data += ' [4] Managing Ethereum node\n'
    data += ' [5] Calculate Blockchain\n'
    data += ' [0] Exit\n'
    print(data)
    number = input(" Number~# ")
    if number == '1':
        print("\n [***] Install ActiveMQ ...")
        # Appel de la fonction pour définir la variable globale
        subprocess.run(["start", "cmd", "/c", "python", "activeMQ.py"], shell=True)
        clear()
        data = ""
    elif number == '2':
        print("\n [***] Launch ActiveMQ...\n")
        webbrowser.open('http://127.0.0.1:8161')
        clear()
        data = ""
    elif number == '3':
        print("\n [***] Voltaic data entry ...\n")
        time.sleep(5)
        clear()
        data = ""
    elif number == '4':
        print("\n [***] Managing Ethereum node ...\n")
        command = ["docker", "exec", "-it", "ethereum", "geth", "attach", "ipc:/tmp/geth.ipc"]
        result = subprocess.run(command, stdout=subprocess.PIPE)

        print(result.stdout.decode())

        # Connexion au nœud Ethereum
        print("Connexion au nœud Ethereum")
        w3 = Web3(Web3.HTTPProvider('http://localhost:8545'))

        # Ajout du middleware POA
        print("")
        print("Ajout du middleware POA")
        w3.middleware_onion.inject(geth_poa_middleware, layer=0)

        # Affichage de l'adresse du compte par défaut
        print("")
        print("Affichage de l'adresse du compte par défaut")
        print(w3.eth.coinbase)

        # Affichage de tous les comptes disponibles
        print("")
        print("Affichage de tous les comptes disponibles")
        print(w3.eth.accounts)

        # Affichage du solde du premier compte
        print("")
        print("Affichage du solde du premier compte")
        print(w3.eth.getBalance(w3.eth.accounts[0]))

        # Création d'un nouveau compte
        print("")
        print("Création d'un nouveau compte")
        new_account_1 = w3.geth.personal.new_account("123456")
        print(new_account_1)

        # Affichage du solde du premier compte après création du nouveau compte
        print("")
        print("Affichage du solde du premier compte après création du nouveau compte")
        print(w3.eth.getBalance(w3.eth.accounts[0]))

        # Création de deux nouveaux comptes supplémentaires
        print("")
        print("Création de deux nouveaux comptes supplémentaires")
        new_account_2 = w3.geth.personal.new_account("123456")
        new_account_3 = w3.geth.personal.new_account("123456")

        # Affichage de tous les comptes disponibles
        print("")
        print("Affichage de tous les comptes disponibles")
        print(w3.eth.accounts)

        # Envoi de 1000 wei du compte par défaut vers le deuxième compte
        print("")
        print("Envoi de 1000 wei du compte par défaut vers le deuxième compte")
        tx_hash = w3.eth.sendTransaction({'from': w3.eth.coinbase, 'to': new_account_2, 'value': 1000})
        print(tx_hash)

        # Affichage du solde du deuxième compte
        print("")
        print("Affichage du solde du deuxième compte")
        print(w3.eth.getBalance(new_account_2))

        time.sleep(5)
        clear()
        data = ""
    elif number == '5':
        print("\n [***] Calculate Blockchain ...\n")
        subprocess.Popen(['python', 'blockchain.py'], creationflags=subprocess.CREATE_NEW_CONSOLE)
        time.sleep(5)
        clear()
        data = ""
    elif number == '0':
        print('\n [+] Good Bye ' + platform.uname()[1] + ' !\n')
        quit()
    else:
        print("\n [X] Error !\n [!] Select this number: 1, 2, 3, 4, or 0\n")
