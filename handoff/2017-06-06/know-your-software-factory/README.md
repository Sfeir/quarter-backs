Know your software factory
==========================

Pré-requis
----------
* Docker (testé avec 4 Go de ram)
* Docker-compose
* Internet

Installation
------------

Depuis le dossier `docker-sf`, lancez la commande `$ docker-compose up`.
Vous verrez les logs de tous les containes (nginx, scm-manager, jenkins, sonar et nexus).

Vous pourrez arrêter les containers avec `Ctrl+C`. Pour les relancer en tâche de fond : `$ docker-compose up -d`.
Pour arrêter les containers : `$ docker-compose stop`.


Configuration
-------------

### SCM Manager
Ouvrez la page http://localhost/scm et identifiez-vous. Le compte admin par défaut est `scmadmin/scmadmin`.

Dans le menu de navigation à gauche, ouvrez `Config > General`. Cochez l'option _Allow Anonymous Access_ pour que vos dépôts publics soient accessibles anonymement. Sauvez.

Dans l'onglet `Repositories`, créez un nouveau dépôt avec le bouton `Add`.
Dans l'onglet `Settings` qui vient de s'ouvrir en bas de page, nommez votre dépôt `sfeir-demo`, choisissez le type `Git` et cochez la case `public`.

Copiez l'adresse du dépôt http://localhost/scm/git/sfeir-demo.

### Push du projet test
Clonez le dépôt dans un dossier temporaire :
`$ git clone http://localhost/scm/git/sfeir-demo /tmp/sfeir-demo`.

Copiez le **contenu** du projet de test dans cette copie du dépôt, commitez et pushez le tout (vous devez avoir le fichier Jenkinsfile à la racine du dossier `/tmp/sfeir-demo/`).

Vous avez des liens vers les commits et les sources dans l'onglet `sfeir-demo` sous la liste des dépôts dans SCM Manager. Dans les sources vous devriez voir 3 fichiers `pom.xml, settings.xml, Jenkinsfile` et le dossier `src`.

### Nexus
Ouvrez la page http://localhost/nexus/ et identifiez-vous. Le compte admin par défaut est `admin/admin123`. L'écran d'administration est accessible via l'engrenage en haut de page.

Dans la partie _Roles_, créez un nouveau role avec `deployer` comme id et nom. Ajoutez les privilèges `nx-repository-view-maven2-maven-releases-*` et `nx-repository-view-maven2-maven-snapshots-*`.

Dans la partie _Users_, créez un utilisateur `sfeir` avec les roles `nx-anonymous` et `deployer`, et le mot de passe `sfeir`.

### Sonar
Ouvrez la page http://localhost/sonar/ et identifiez-vous. Le compte admin par défaut est `admin/admin`.

Dans la partie **Administration > Security > Users**, créez un utilisateur `jenkins` avec un mot de passe de votrre choix.

Cliquez ensuite sur l'icone bleu dans la colonne **Tokens** pour l'utilisateur Jenkins. Entrez un nom pour le token que vous allez générer. **Copiez le token généré en lieu sûr**.

cfac892d12affc854b7afaa5a87e51a0cd28a27c

### Jenkins
Ouvrez la page http://localhost/jenkins/ et identifiez-vous. L'image docker a initialisé un compte admin par défaut `admin/admin`.

Ouvrez la page **Administrer Jenkins > Configurer le système**.
Dans la section _SonarQube Server_, ajoutez une installation Sonar nommée _Sfeir Sonar_ pointant sur l'url http://nginx/sonar (voir `(1)` ). Insérez le token que vous venez de générer sur Sonar. Enregistrez.


Ouvrez la page **Administrer Jenkins > Configurer globale des outils**.
Dans la section _Maven_, ajoutez une installation nommée `Maven 3.5.0` qui s'installe automatiquement depuis Apache. Choisissez la version `3.5.0`. Enregistrez.


**Création du pipeline**

Ouvrez la nouvelle interface _Blue Ocean_ de Jenkins avec le bouton en haut de page. Créez un nouveau pipeline Git. Renseignez comme URL de dépot `http://nginx/scm/git/sfeir-demo` (voir `(1)`). Validez la création du pipeline qui devrait immédiatement lancer un build.

### (1) Note sur Docker
_Dans cette démo nous n'avons pas de DNS externe à Docker. Du coup le proxy Nginx n'a pas de nom DNS utilisable par votre navigateur web. À la place, nous utilisons `http://localhost` qui correspond au port 80 de votre machine, associé au port 80 du container Nginx.
Dans un container docker par contre, `http://localhost` ne pointe pas sur le port 80 de votre machine, mais sur le port 80 du container lui-même.
Pour cette raison, les liens inter-containers pointent sur `http://nginx`, de façon à ce que les containers puisse se joindre entre eux en passant par le proxy Nginx.
En production vous n'auriez pas ce problème._


--

Julien Furgerot `@CuriousJfu`

_furgerot.j@sfeir.com_
