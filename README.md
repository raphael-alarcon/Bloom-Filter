# Rapport d'implémentation d'un Filtre de Bloom

## Sommaire
- [Description](#filtre-de-bloom)
- [Statut du projet](#statut-du-projet)
- [Implémentation](#implmentation)
- [Benchmark]()

<br>

## Filtre de Bloom

<i>« Un filtre de Bloom est une structure de données permettant de façon efficace de tester la présence
ou non d’un élément dans le filtre. En particulier, le filtre de Bloom permet de tester :
-  avec certitude : l'absence d'un élément (si le test indique que le filtre ne contient pas une
valeur, alors il ne la contient pas)
-  avec une certaine probabilité : la présence d'un élément (si le test indique que le filtre
contient une valeur, il peut se tromper avec une certaine probabilité)

Un filtre de Bloom est constitué d’un tableau de bits (ou de booléens) de m cases et de k fonctions
de hachage. Chacune de ses k fonctions de hachage associe, à chaque élément à stocker, un indice
dans le tableau (donc entre 0 et m-1). »</i>

<br>

## Statut du projet
### Avancement 

#### Implémentation des différentes variantes du filtre en utilisant trois types de structure de données
- [x] Simple tableau (<code>int[]</code>)
- [x] ArrayList
- [x] LinkedList

#### Benchmarking (Banc d'essai)
- [x] Banc d'essai temps d'execution
- [x] Banc d'essai taux de faux positif
- [ ] Analyse des résultats

#### Autres
- [ ] Documentation
- [ ] Nettoyage et optimisation
- [ ] Dernières vérifications

### Statut global
![](https://geps.dev/progress/80)

## Implémentation

### Structure du code Java (diagramme de classes UML)
![](/resources/Bloom-Filter.png)


## Benchmark

Afin d'obtenir des résultats exploitables et précis, j'ai choisi de mettre l'accent sur la visualisation des données résultant du benchmarking.
Le but final étant d'effectuer une comparaison des temps d'exécution entre les trois structures de données implémentées (ArrayList, simple "tableau", LinkedList).

### Temps d'exécution

Ici, on compare les temps d'exécution d'un nombre fixé de recherches (valeur aléatoire) dans le filtre pour chacune trois structures de données *(On négligera pour le moment les résultats de ces recherches)*.

![](resources/Comparaison%20tps%20d'éxec..png)

### Taux de faux positif

Le taux de faux positif correspond au nombre de fois où une valeur non présente dans le filtre (qui n'a pas été ajoutée) est retournée présente. 

<hr>

#### Exemple :

On ajoute tour à tour les entiers de la liste [1, 3, 4] dans un filtre de taille *m* = 300. On cherche à tester si l'entier 13 est présent dans le filtre. La fonction de recherche nous stipule que cette valeur est présente alors qu'elle ne l'est pas, il s'agit donc d'un faux positif.

<hr>

Ici, on cherchera donc à tester le ratio de faux positif dans un filtre de taille fixée, mais avec des valeurs de *n* (0.01, 0.05, 0.1) et de *k* (1, 3, 5) différentes.

- *n* ⇨ Nombre de valeurs ajoutées au filtre (en fonction de *m*, si *m* = 500 et *n* = 0.1, 500 x 0.1 = 50)
- *k* ⇨ Nombre de fonctions de hash

## Conclusion 

This was a 3 week long project built during my third module at Turing School of Software and Design. Project goals included using technologies learned up until this point and familiarizing myself with documentation for new features.  

Originally I wanted to build an application that allowed users to pull data from the Twitter API based on what they were interested in, such as 'most tagged users'. I started this process by using the `create-react-app` boilerplate, then adding `react-router-4.0` and `redux`.  

One of the main challenges I ran into was Authentication. This lead me to spend a few days on a research spike into OAuth, Auth0, and two-factor authentication using Firebase or other third parties. Due to project time constraints, I had to table authentication and focus more on data visualization from parts of the API that weren't restricted to authenticated users.

At the end of the day, the technologies implemented in this project are React, React-Router 4.0, Redux, LoDash, D3, and a significant amount of VanillaJS, JSX, and CSS. I chose to use the `create-react-app` boilerplate to minimize initial setup and invest more time in diving into weird technological rabbit holes. In the next iteration I plan on handrolling a `webpack.config.js` file to more fully understand the build process.