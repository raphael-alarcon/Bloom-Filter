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

Voici un graphique représentant ce taux de faux positifs (avec m = 300) :
![](../Bloom-Filter/resources/Taux%20de%20faux%20positif.png)
Ici, *n* est respectivement égal à 1%, 5% et 10%. On ajoute donc 3, 15 et 30 valeurs dans le filtre. 

On remarque que plus on à de fonctions de hash (*k tend vers +∞*), plus le taux de faux positif est élevé, mais également que plus on ajoute de valeurs dans le filtre, plus ce même taux augmente. 
## Conclusion

