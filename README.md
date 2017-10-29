**Projet Web 2018 : 5A - Filmo**
-------------------------------------------------------

Groupe de travail - 5A UFA
-------------------------------------------------------
* CUISINIER Clément
* IANCU Raphaël
* NGUYEN Thanh Tam
* RACHEDI Zakaria
* SOUMARE Ahmed

Enoncé
-------------------------------------------------------
Réaliser un site web, exemple un blog, avec un chat instantané avec une base de données.

**Date de rendu** : 29 Octobre 2017 avant 22h

**Email** : projet2017@filigrane-technologie.fr

**Contenu du mail:** URL du github + sources zippées + readme 

présentant le projet, les choix, les fonctionnalités ... + Un tuto INSTALL

Projet réalisé
-------------------------------------------------------
Nous avons décidé de réaliser un blog sur les films appelé Filmo.
Ce site regroupe les films qui sont actuellement à l'affiche, qui vont bientôt sortir, les mieux notés et les plus populaires; en cliquant dessus vous pouvez accéder au détail de chacun de ces films. Il est également possible de faire une recherche sur n'importe quel film que vous souhaitez retrouver.
De plus, afin d'accéder au chat instantané, il est nécessaire de se connecter avec un compte google, facebook, twitter, github ou anonymement, selon votre choix.

Site accessible en ligne
--------------------------------------------------------
Le site a été déployé en ligne pour qu'il soit accessible par tous.
Vous pouvez y accéder à l'URL suivante: [filmo.riancu.com](http://filmo.riancu.com)

Tutoriel d'installation
--------------------------------------------------------
* Télécharger et dézipper le projet depuis GitHub.
* Ouvrir un terminal dans le répertoire du projet ProjetWeb2018.
* Taper la commande `$ npm install ` pour installer toutes les dépendances
* Une fois terminé, taper la commande `$ ng serve`
* Lorsque le projet a terminé de compiler, aller dans votre navigateur à l'URL suivante: [localhost:4200](http://localhost:4200)

Axes d'amélioration
--------------------------------------------------------
Il aurait été possible d'ajouter de nouvelles fonctionnalités au site mais par manque de temps, étant uniquement apprentis dans ce groupe, nous n'avons pas pu.
Ces fonctionnalités aurait pu être:
* De visualiser les films que l'on a aimé
* De discuter dans un chat privé
* De mettre une note et un commentaire aux films
* De pouvoir visualiser la bande d'annonce

Choix de technologies
--------------------------------------------------------
* Angular 

Ce framework nous a permis d’adopter une structure MVC pour notre site. Cette structure nous offre un code clair, propre et organisé.
* Firebase

Nous avons choisi Firebase pour héberger plus simplement et en toute sécurité notre base de données NoSQL. Firebase permet également de faciliter les connexions à partir de comptes déjà existants comme Google Account, Facebook, Twitter ou GitHub.
* API The Movie Database

Avec cette API, nous sommes sur que tous les films existants y sont répertoriés. De plus, les films sont régulièrement mis à jour, cela permet d’être en phase avec ce qui est et ce qui sera présent au cinéma.
* Bootstrap

Avec le framework front-end « Bootstrap », nous avons pu mettre en place un design complètement responsive, s’adaptant à toutes les tailles de devices. 
* JSON

Les requêtes HTTP s'effectuent en REST / JSON
* Trello

Nous avons utilisé Trello comme outil de gestion de projet et ainsi répartir et voir à quels stades étaient les tâches de chacun d’entre nous.