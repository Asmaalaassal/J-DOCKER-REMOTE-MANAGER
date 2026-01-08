 🐳 J-Docker Remote Manager

 📌 Description

**J-Docker Remote Manager** est un projet Java client/serveur permettant de piloter le moteur Docker d'une machine distante via une console CLI. Il s'inscrit dans le cadre du module **GTR 3** et illustre les concepts de **programmation réseau**, **architecture distribuée**, et **administration Docker à distance**.

L'application repose sur une architecture **Client CLI / Serveur Java multithread**, communiquant via le protocole **TCP**, où le serveur agit comme une passerelle entre les clients distants et l'API Docker.

🏗️ Architecture globale

```
Client CLI (Java)
        │ TCP (port 5000)
        ▼
Serveur Java (Multithread)
        │ Docker API REST (port 2375)
        ▼
Docker Engine (VM Debian)
```

 🔹 Serveur Java

* Déployé sur une **VM Debian**
* Écoute les connexions TCP des clients
* Gère plusieurs clients simultanément (Thread par client)
* Communique avec Docker via l'API REST exposée sur le port `2375`

🔹 Client CLI

* Application Java en ligne de commande
* Permet à l'administrateur de se connecter au serveur distant
* Envoie des commandes textuelles et reçoit les réponses du serveur
* 
⚙️ Technologies utilisées

* **Java 17**
* **Docker Engine**
* **Docker Remote API (2375)**
* **Sockets TCP (java.net)**
* **Maven** (gestion des dépendances)
* **Bibliothèque docker-java**

 🧪 Prérequis

### Serveur (VM Debian)

* Docker installé et fonctionnel
* Docker configuré pour écouter sur le port TCP `2375`
* Java JDK 17 installé

Exemple de configuration Docker :

```
/etc/docker/daemon.json
```

```json
{
  "hosts": ["unix:///var/run/docker.sock", "tcp://0.0.0.0:2375"]
}
```

Puis :

```bash
sudo systemctl restart docker
```

### Client

* Java JDK 17
* Accès réseau vers la VM (IP + port 5000)

---

 🚀 Lancement du projet
 1️⃣ Démarrer le serveur (sur la VM Debian)

```bash
java -jar DockerServer.jar
```

Résultat attendu :

```
Docker Server démarré sur le port 5000
```
 2️⃣ Lancer le client (machine locale)

```bash
java -jar DockerClientCLI.jar
```

Résultat attendu :

```
Connecté au serveur Docker à <IP_VM>
docker>
```
 ✅ Fonctionnalités actuelles

* Connexion client/serveur via TCP
* Serveur Java multithread
* Accès distant au moteur Docker
* Architecture extensible pour l'ajout de commandes Docker

 🔒 Robustesse

* Une déconnexion client n'affecte pas le serveur
* Gestion indépendante de chaque client
* Base prête pour la gestion d'erreurs Docker

 📈 Évolutions possibles

* Commandes Docker (`docker ps`, `docker images`, `docker run`)
* Gestion du cycle de vie des conteneurs
* Streaming des logs en temps réel
* Protocole JSON pour les échanges
* Authentification des clients

🎓 Contexte académique

* **Université Cadi Ayyad**
* **École Nationale des Sciences Appliquées de Safi (ENSA Safi)**
* Département : **IRT**
* Module : **GTR 3**

 👨‍💻 Auteur

Projet réalisé dans le cadre académique par:
LAASSAL Asmaa 
SRIJA Fatimazahra
OUYAHIA Salma
WARDY Zakia
