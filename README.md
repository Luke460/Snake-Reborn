# Snake-Reborn
Il gioco si svolge all'interno di una mappa di 6 stanze (in futuro cambierà le sue dimensioni a seconda del numero di giocatori umani).

Ci sono 3 tipi di bot: verdi, arancioni e rossi.
 - i verdi evitano solo le collisioni con i muri e seguono il cibo nelle immediate vicinanze della testa.
 - gli arancioni evitano quasi tutte collisioni e seguono il cibo come i verdi.
 - i rossi evitano ogni tipo di collisione (se possibile) e seguono il cibo in maniera più avanzata.

Il giocatore deve raccogliere il cibo all'interno della mappa e tentare di uccidere i vari bot per rubare il cibo da loro preso (anche i bot potranno fare lo stesso nei confronti del giocatore).

Per uccidere un serpente è necessario tagliargli la strada.

Il punteggio-per ora-viene visualizzato come titolo della finestra e salvato all'interno del file di testo "record.txt".

Sono state implementate 2 diverse configurazioni di comandi (dettagli nel file README.txt).
