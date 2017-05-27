# Snake-Reborn
Un gioco ispirato al celebre Snake.

Il gioco si svolge al'interno di una mappa di 6 stanze (in futuro cambierà le sue dimensioni a seconda del numero di giocatori umani).
Ci sono due tipi di bot: verdi e rossi.
-i verdi evitano le collisioni con i muri e seguono il cibo nelle vicinanze della testa.
-i rossi evitano ogni tipo di collisione (se possibile) e seguono il cibo come i verdi.

Il giocatore deve raccogliere il cibo all'interno della mappa e tentare di uccidere i vari bot per rubare il cibo da loro preso (anche i bot potranno fare lo stesso nei confronti del giocatore).

Per uccidere un serpente è necessario tagliargli la strada.

Il punteggio-per ora-viene visualizzato come titolo della finestra e salvato all'interno del file di testo "record.txt".

Sono state implementate 2 diverse configurazioni di comandi:
 invio -> resuscita il serpente
 esc -> chiudi il gioco
-------------------------------
 freccia dx -> gira a destra
 freccia sx -> gira a sinistra
-------------------------------
 A -> vai a sinistra
 D -> vai a destra
 W -> vai in alto
 S -> vai in basso
