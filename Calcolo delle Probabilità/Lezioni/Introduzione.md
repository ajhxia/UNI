- [[#Probabilità di un evento|Probabilità di un evento]]
			- [[#Eventi come insiemi|Eventi come insiemi]]
			- [[#Proprietà  basilari delle operazioni booleane su insiemi|Proprietà  basilari delle operazioni booleane su insiemi]]
			- [[#Spazi di probabilità finiti|Spazi di probabilità finiti]]
				- [[#Spazi di probabilità finiti#L'evento impossibile ha proprietà nulla|L'evento impossibile ha proprietà nulla]]
				- [[#Spazi di probabilità finiti#Proprietà di additività finita|Proprietà di additività finita]]
	- [[#Probabilità di un evento#Prime proprietà della probabilità|Prime proprietà della probabilità]]
			- [[#Proprietà di Base|Proprietà di Base]]
			- [[#Proprietà del Complementare|Proprietà del Complementare]]
			- [[#Proprietà di Monotonia|Proprietà di Monotonia]]
			- [[#Probabilità di B\\A|Probabilità di B\\A]]
			- [[#Formula di inclusione ed esclusione per due eventi|Formula di inclusione ed esclusione per due eventi]]
			- [[#Proprietà delle partizioni (dell'evento certo)|Proprietà delle partizioni (dell'evento certo)]]
			- [[#Una partizione particolare|Una partizione particolare]]
				- [[#Una partizione particolare#Esempio Pratico|Esempio Pratico]]
			- [[#Come definire una funzione p : Ω → [0, 1]|Come definire una funzione p : Ω → [0, 1]]]
				- [[#Come definire una funzione p : Ω → [0, 1]#Esempio|Esempio]]
	- [[#Probabilità di un evento#Spazi di probabilità numerabili|Spazi di probabilità numerabili]]
			- [[#L’additività numerabile implica l’additività finita|L’additività numerabile implica l’additività finita]]
			- [[#Proprietà degli spazi numerabili|Proprietà degli spazi numerabili]]
				- [[#Proprietà degli spazi numerabili#Estensioni a partizioni numerabili|Estensioni a partizioni numerabili]]

## Probabilità di un evento
La probabilità di un evento viene indicata da un numero reale e spesso si usa $p$ e per convenzione è sempre un numero tra 0 e 1.

Si parla di Probabilità classica (Uniforme) quando i casi possibili sono un numero finito e la probabilità di un evento è valutata come il numero dei casi favorevoli sul numero dei casi possibili. La **probabilità cambia** con l'informazione che abbiamo a disposizione.

##### Eventi come insiemi
Ogni evento viene descritto da un sottoinsieme di un insieme $\Omega$, ovvero da un elemento dell'insieme delle parti di esso, che denotiamo come $\mathcal{P}(\Omega)$.
L'insieme $\Omega=\{ω1, ..., ωN\}$ rappresenta i **casi possibili** nell'esperimento che ci interessa e viene definito come ==spazio campione==. Un **evento** viene descritto da un sottoinsieme di omega, ossia da un elemento dell'insieme delle parti esso **P(Ω)**.

Dati due eventi $E1, E2$ rappresentati da due sottoinsiemi $A1, A2$ di $Ω$:
-  L’evento (E1 ‘’oppure” E2) si verifica se e solo se si verifica un evento elementare ωi che appartiene ad almeno uno dei due insiemi A1 o A2, ossia se appartiene all’insieme $A1 ∪ A2$
- L’evento (E1 e E2) si verifica se e solo se si verifica un evento elementare ωi che appartiene sia ad A1 che ad A2, ossia ad $A1 ∩ A2.$
-  L’evento (‘’negazione” di E1) si verifica se e solo se si verifica un evento elementare ωi che non appartiene ad A1, ossia che appartiene ad A1, il **complementare di A1**.

$A ⊆ B$ significa che ogni evento elementare che rende verificato A rende verificato anche B e dunque interpretiamo la relazione $A ⊆ B$ come “A implica B”.

$\Omega$ è un evento vero qualunque evento elementare si verifichi, in quanto esso contiene tutti gli eventi elementari e dunque interpretiamo $Ω$ come **l’evento certo**.

$∅$, l’insieme vuoto, non contenendo alcuno degli eventi elementari possibili, è un evento che non è mai verificato; dunque interpretiamo $∅$ come **l’evento impossibile**.

$A ∪ B = Ω$ significa che l’evento costituito dal verificarsi di almeno uno dei due eventi A o B coincide con l’evento certo Ω; dunque interpretiamo tale condizione come A e B sono *esaustivi* (è certo che se ne verifichi almeno uno dei due).

$A ∩ B = ∅$ significa che l’evento costituito dal verificarsi di entrambi gli eventi A e B coincide con l’evento impossibile $∅$; dunque interpretiamo la condizione $A ∩ B = ∅$ come A e B sono *incompatibili* (è certo che se ne verifichi al più uno dei due, ovvero che se ne verifichi al massimo uno dei due).

Consideriamo una collezione di sottoinsiemi dello spazio $Ω$ 
$\mathcal{H} =  \{H1, ..., H_{m}\}$, con $H_{ℓ} ∈ \mathcal{P}(Ω),\text{ } ℓ = 1, ..., m.$

Tale collezione costituisce una **partizione** di $\Omega$  se e solo se
$H_{ℓ1} ∩ H_{ℓ2} = ∅$ $ per $ℓ1 \ne ℓ2$ ossia:
$$\bigcup_{ℓ=1}^{m}H_ℓ=Ω$$

$H_1, ..., H_m$ sono degli eventi e sono a due a due incompatibili (non se ne possono verificare due contemporaneamente), ma sono esaustivi (sicuramente se ne verifica uno).  Questo significa che  è certo che si verifichi uno ed uno soltanto tra $H_{1},\dots,H_{m}$.

Gli insiemi/eventi $H_{l}$ sono detti **elementi della partizione**.

##### Proprietà  basilari delle operazioni booleane su insiemi
- Doppia negazione: $$\bar{\bar{A}}=A$$
- Proprietà distributiva dell’unione rispetto all’intersezione:$$ (A ∪ B) ∩ C = (A ∩ C) ∪ (B ∩ C).$$Estensione: $$\left( \bigcup_{i \in I}A_{i}  \right)\cap C = \bigcup_{i \in I}(A_{i} \cap C)$$
- Proprietà distributiva dell’intersezione rispetto all’unione: $$(A ∩ B) ∪ C = (A ∪ C) ∩ (B ∪ C).$$Estensione: $$\left( \bigcup_{i \in I}A_{i} \right) \cup C = \bigcup_{i \in I}(A_{i} \cup C)$$
- Legge di De Morgan: $$\overline{A \cap B} = \bar{A} \cup \bar{B} \quad\text{ equivalente ad} \quad A \cap B = \overline{(\bar{A} \cup \bar{B})}$$Estensione: $$\overline{{\bigcap_{i \in I}A_{i} }}= \bigcup_{i \in I}\bar{A_{i}} \quad\text{oppure}\quad \bigcap_{i \in I}A_{i} = \overline{\bigcup_{i \in I}\bar{A_{i}}}$$
Quindi, segare il verificarsi di A e B equivale a richiedere il verificarsi una tra la negazione di A e la negazione di B.

##### Spazi di probabilità finiti
Uno spazio finito di probabilità è una terna $(Ω,\mathcal{P}(Ω), \mathbb{P})$ dove:
- $\Omega$ è un insieme finito
- $\mathcal{P}(Ω)$è la famiglia delle parti di $\Omega$ 
- $P$è una misura di probabilità, ossia una probabilità su $(Ω,\mathcal{P}(Ω))$, Ossia è una funzione che soddisfa i seguenti assiomi: 
	-  $\mathbb{P} : \mathcal{P} (Ω) → [0, 1]$
	- $\mathbb{P} (Ω) = 1$ **(condizione di normalizzazione)**
	- Per $E_{1}\cap E_{2}=∅ \text{ allora } \mathbb{P}(E1 ∪ E2) = \mathbb{P}(E1) + \mathbb{P}(E2)$ **(proprietà di additività finita).** Possiamo dire che se $E_{1}$ ed $E_{2}$ sono eventi incompatibili (non si possono verificare insieme), allora $\mathbb{P}(E_{1}\cup E_{2})=\mathbb{P}(E_{1})+\mathbb{P}(E_{2})$ (si verifica almeno uno tra $E_{1}$ ed $E_{2}$).

###### L'evento impossibile ha proprietà nulla
Siano $E_{1}=E_{2}$ allora $E_{1}\cap E_{2} = ∅$ e quindi, per la proprietà di additività finita:
$$\begin{align*} \mathbb{P}(E_1 \cup E_2) &= \mathbb{P}(E_1) + \mathbb{P}(E_2) \\ ⇕  \\\mathbb{P}(\emptyset \cup \emptyset) &= \mathbb{P}(\emptyset) + \mathbb{P}(\emptyset) \\ ⇕ \\ \mathbb{P}(\emptyset) &= 2\mathbb{P}(\emptyset) \\ ⇕ \\ \mathbb{P}(\emptyset) &= 0 \end{align*}$$

###### Proprietà di additività finita
La proprietà iii) di additività si generalizza alla seguente: Siano $E_{1}, ..., E_{n} \in P(\Omega)$ disgiunti a due a due, ovvero tali che: 
$$E_{i} ∩ E_{j} = ∅ \quad\text{per}\quad i, j ∈ \{1, 2, · · · , n\}, \quad\text{con}\quad i ̸= j;$$

Allora si ha:
$$
\mathbb{P}\left(\bigcup_{i=1}^{n}E_{i}\right) = \sum_{i=1}^{n} \mathbb{P}(E_{i})
$$
- La probabilità dell'unione dei sottoinsiemi di $\Omega$ è uguale alla sommatoria delle probabilità dei sottoinsiemi di omega.

La dimostrazione si ottiene facilmente per induzione su n: caso n=3
$$\begin{align*}
E1 ∩ E2 = ∅\text{, }E1 ∩ E3 = ∅ \text{ e } E2 ∩ E3 = ∅ \\ (E1 ∪ E2) ∩ E3 = (E1 ∩ E3) ∪ (E3 ∩ E3) = ∅ \\ \\ \mathbb{P} (E1 ∪ E2 ∪ E3) = \mathbb{P}  (E1 ∪ E2) ∪ E3 = \mathbb{P}  (E1 ∪ E2) + \mathbb{P} (E3) = \\ \\ \mathbb{P} (E1) + \mathbb{P} (E2) + \mathbb{P} (E_{3})\end{align*}$$


### Prime proprietà della probabilità
##### Proprietà di Base
$$\mathbb{P}(A) = \mathbb{P}(A ∩ B) +\mathbb{P}(A ∩ \bar{B})$$e $$\mathbb{P}(B) =\mathbb{P}(A ∩ B) + \mathbb{P}(\bar{A}∩ B)$$
##### Proprietà del Complementare
$$\mathbb{P}( \bar{E})= 1 − \mathbb{P}(E)$$

***Verifica:*** basta prendere $A=\Omega$ e $B=E$ nella proprietà di base, per cui $$1 = \mathbb{P}(Ω) = \mathbb{P}(Ω ∩ E) + \mathbb{P}(Ω ∩ \bar{E}) = \mathbb{P}(E) + \mathbb{P}(\bar{E})$$
Facendo un esempio pratico, basta pensare ad 1 come il 100%. Se $\mathbb{P}(E)=30\%$ allora per ricavare il suo complementare basta fare $100\% - 30\%$ ossia: $1-\mathbb{P}(E)$ ottenendo il $70\%$.

##### Proprietà di Monotonia
Se $A\subseteq B$  allora risulta $\mathbb{P}(A) ≤ \mathbb{P}(B)$

***Verifica:*** si osserva che se $A\subseteq B$, allora $A\cap B=A$ e quindi, dalla precedente proprietà $$\mathbb{P}(B)= \mathbb{P}(A\cap B)+\mathbb{P}(\bar{A}\cap B)\geq \mathbb{P}(A)$$Dove:
- $\mathbb{P}(A\cap B)=\mathbb{P}(A)$
- $\mathbb{P}(\bar{A}\cap B) \geq 0$

##### Probabilità di B\\A
Ricordando che $B\setminus A=B\cap \bar{A}$ sempre dalla proprietà di base si ha $$\mathbb{P}(B\setminus A) = \mathbb{P}(B ∩ \bar{A}) = \mathbb{P}(B) − \mathbb{P}(A ∩ B)$$
Se inoltre $A\subset B$ allora $A\cap B=A$, e quindi: $$\mathbb{P}(B \setminus A) = \mathbb{P}(B) − \mathbb{P}(A ∩ B) = \mathbb{P}(B) − \mathbb{P}(A)$$

##### Formula di inclusione ed esclusione per due eventi
$$\mathbb{P} (A ∪ B) = \mathbb{P}  (A) + \mathbb{P}  (B) − \mathbb{P}  (A ∩ B)$$

***Verifica*** da una parte: $A ∪ B = (A ∩ B) ∪ (A ∩ B) ∪ (B ∩ A)$
e quindi $\mathbb{P} (A ∪ B) = \mathbb{P} (A ∩ B) +\mathbb{P} (A ∩ \bar{B}) + \mathbb{P} (B ∩ \bar{A})$

Dall'altra parte, per la proprietà di base: $$\begin{align*}
\mathbb{P}  (A) +\mathbb{P}  (B) −\mathbb{P}  (A ∩ B) = \\ \mathbb{P}(A\cap B)+ \mathbb{P}(A \cap \bar{B}) + \mathbb{P}(A\cap B)+\mathbb{P}(\bar{A}\cap B)-\mathbb{P}(A\cap B)
\end{align*}$$

##### Proprietà delle partizioni (dell'evento certo)
Sia $\mathcal{H} = \{H1, ..., Hn\}$ una partizione, ossia:
$$\bigcup_{i=1}^{n}H_{i}=\Omega \qquad H_{i}\cap H_{j}=∅ \quad\text{per}\quad i\ne j$$
Allora si ha:
$$\sum_{i=1}^{n}\mathbb{P}(H_{i}=1)$$

***Verifica:*** $$1=\mathbb{P}\left( \bigcup_{i=1}^{n}H_{i} \right)=\sum_{i=1}^{n}\mathbb{P}(H_{i})$$Dove la prima uguaglianza vale per la proprietà di normalizzazione, e l'ultima per l'additività finita in quanto gli eventi $H_{i}$ sono incompatibili a due a due.

Un altro modo per definire la *proprietà delle partizioni* si ha avendo $\mathcal{H} = \{H1, ..., Hn\}$ una partizione, ossia: $$\bigcup_{i=1}^{n}H_{i}=\Omega\quad H_{i}\cap H_{j}=∅ \quad \text{per }\quad i\ne j$$allora per ogni evento E si ha
$$\mathbb{P}(E)=\sum_{i=1}^{n}\mathbb{P}(H_{i}\cap E)$$

***Verifica:*** $$E = E ∩ Ω = E\cap\bigcup_{i=1}^{n}H_{i}=\bigcup_{i=1}^{n}(E\cap H_{i})$$per la proprietà distributiva dell'unione rispetto all'intersezione: $$\mathbb{P}(E)=\mathbb{P}\left( \bigcup_{i=1}^{n}(E\cap H_{i}) \right)=\sum_{i=1}^{n}\mathbb{P}(E\cap H_{i})$$per additività finita: gli eventi $E\cap H_{i}$ sono incompatibili a due a due, in quanto sottoinsiemi di eventi incompatibili a due a due.

##### Una partizione particolare 
In particolare, si può considerare la partizione $$\mathcal{H}=\{H_{i}=\{\omega_{i}\}, \quad  i=1,2,3, \dots, N\}$$ e quindi, posto $p(\omega_{i}):=\mathbb{P}({\omega_{i}}), \quad i=1,\dots, N$ risulta$$p(\omega_{i})\geq 0 \quad \sum_{i=1}^{N}p(\omega_{i})=1$$

Una probabilità $E → P(E)$ è una funzione su $P(Ω)$, l’insieme delle parti di $Ω$. Se $Ω = {ω1, ...., ωN}$, allora la cardinalità di $Ω$ e di $P(Ω)$ valgono:
$$|\Omega|=N \quad |\mathcal{P}(\Omega)|=2^{N}$$

Calcolo di $\mathbb{P}(E)$ $$\mathbb{P}(E) = \sum_{{i:\omega \in E}}p(\omega_{i}) = \sum_{{\omega \in E}}p(\omega) $$in quanto, se $E=\{\omega_{1}, \omega_{2}, \dots, \omega_{i_{k}}\}=\{\omega_{i_{1}}\}\cup \{\omega_{i_{2}}\}\cup \dots \cup \{\omega_{i_{k}}\}$ e quindi $\mathbb{P}(E)= \mathbb{P}(\{\omega_{i_{1}}\})+ \mathbb{P}(\{\omega_{i_2}\})+ \dots + \mathbb{P}(\{\omega_{i_{k}}\})$ = $p(\omega_{i_{1}}) + p(\omega_{i_{2}})+ \dots + p(\omega_{i_{k}})$

In parole semplici, la probabilità di un evento è la somma della probabilità di tutti i suoi eventi elementari.

Quindi, possiamo ricavarci la probabilità dell'evento partendo dalla probabilità dell'evento elementare. 
Abbiamo appena visto che, data $\mathbb{P}$, si ricava la funzione $p : Ω → [0, 1$], definita come $p(ω) = P({ω})$, dalla quale a sua volta si può di nuovo ricavare $E→ P(E)$.
Può essere conveniente fare il percorso inverso, ossia, partire da una funzione $ω ∈ Ω → p(ω)$ con le proprietà $$p(\omega_{i}) \geq 0, i=1,2, \dots, N \quad \sum_{i=1}^{N}p(\omega_{i})=1$$e definire una funzione $\mathbb{P}$ su $\mathcal{P}(\Omega))$. $$E \to \mathbb{P}(E) = \sum_{{i:\omega \in E}}p(\omega_{i}) = \sum_{{\omega \in E}}p(\omega) $$

E facile convincersi che grazie alle proprietà $$p(\omega_{i}) \geq 0, i=1,2, \dots, N \quad \sum_{i=1}^{N}p(\omega_{i})=1$$la funzione $$E \to \mathbb{P}(E) := \sum_{{\omega \in E}}p(\omega) $$è una ***Probabilità*** che soddisfa i tre assiomi.
Le prime due proprietà sono banali, mentre la terza(additività) deriva da: se $E=\{\omega_{j_{1}}, \omega_{j_{2}}, \dots,\omega_{j_{k}}\} \subset \Omega$ ed $F=\{\omega_{j_{1}}, \omega_{j_{2}}, \dots,\omega_{j_{k}}\}$ e $E \cap F = \emptyset$
allora possiamo dire che: $P(E ∪ F) = \{ p(ω_{j_{1}} ) + p(ω_{j_{2}} ) + ... + p(ω_{j_{m}}) \}+ \{p(ω_{k_{1}}) + p(ω_{k_{2}}) + ... + p(ω_{k_{r}})\}$Che è pari a:
$$\mathbb{P}(E \cup F)=\sum_{h=1}^{m}=p(\omega_{j_{h}})+\sum_{ℓ=1}^{r}=p(\omega_{k_{ℓ}}) = \mathbb{P}(E) + \mathbb{P}(F)$$

###### Esempio Pratico
Sia $Ω = {a, b, c, d}$ (possiamo pensare $ω1 = a, ω2 = b, ω3 = c, ω4 = d$) e siano $p(a) = 1/8, p(b) = 1/4, p(c) = 1/2 p(d) = 1/8.$ Chiaramente $p(a), p(b), p(c), p(d) ≥ 0$ e $p(a) + p(b) + p(c) + p(d) = 1$. 
Allora la probabilità definita sull’insieme delle parti di $Ω = {a, b, c, d}$ tramite la formula$$\mathbb{P}(E)=\sum_{\omega \in E}=p(\omega)$$è data dalla funzione che è specificata nella seguente tabella.
$$\begin{align*}
& ∅ → \mathbb{P}(∅) = 0 \\ 
&\{a\} → \mathbb{P}(\{a\}) = p(a) = 1/8 \\ 
&\{b\} → \mathbb{P}(\{b\}) = p(b) = 1/4 \\ 
&\{c\} → \mathbb{P}(\{c\}) = p(c) = 1/2 \\ 
&\{d\} → \mathbb{P}(\{d\}) = p(d) = 1/8 \\
&\{a, b\} → \mathbb{P}(\{a, b\}) = p(a) + p(b) = 1/8 + 1/4 = 3/8 \\
&\{a, c\} → \mathbb{P}(\{a, c\}) = p(a) + p(c) = 1/8 + 1/2 = 5/8 \\
&\{a, d\} → \mathbb{P}(\{a, d\}) = p(a) + p(d) = 1/8 + 1/8 = 1/4 \\ 
&\{a, b\} → \mathbb{P}(\{b, c\}) = p(b) + p(c) = 1/4 + 1/2 = 3/4 \\ 
&\{b, d\} → \mathbb{P}(\{b, d\}) = p(b) + p(d) = 1/4 + 1/8 = 3/8 \\ 
&\{c, d\} → \mathbb{P}(\{c, d\}) = p(c) + p(d) = 1/2 + 1/8 = 5/8 \\ 
&\{a, b, c\} → \mathbb{P}(\{a, b, c\}) = p(a) + p(b) + p(c) = 1/8 + 1/4 + 1/2 = 7/8 \\
&\{a, b, d\} → \mathbb{P}(\{a, b, d\}) = p(a) + p(b) + p(d) = 1/8 + 1/4 + 1/8 = 1/2 \\
&\{a, c, d\} → \mathbb{P}(\{a, c, d\}) = p(a) + p(c) + p(d) = 1/8 + 1/2 + 1/8 = 3/4 \\
&\{b, c, d\} → \mathbb{P}(\{b, c, d\}) = p(b) + p(c) + p(d) = 1/4 + 1/2 + 1/8 = 7/8 
\\ &\{a, b, c, d\} → \mathbb{P}(\{a, b, c, d\}) = \\ &p(a) + p(b) + p(c) + p(d) = 1/8 + 1/4 + 1/2 + 1/8 = 1
\end{align*}$$

##### Come definire una funzione p : Ω → [0, 1]
Sempre nel caso in cui $Ω$ è finito, la funzione può essere definita a meno di un ***fattore di proporzionalità*** , ovvero dati $N$ numeri non negativi (e non tutti nulli) $g(i),\text{ } i = 1, 2, ..., N$,  si pone $p(ω_{i})$ proporzionale a $g(i)$: $$p(ω_{i}) ∝ g(i) ⇔ ∃ K \quad \text{tale che}\quad ∀ i = 1, 2, ..., N \quad \text{si ha} \quad p(ω_{i}) = Kg(i)$$
In tale caso $$\sum_{i}=1^{N}p(w_{i})=1 \iff \sum_{j=1}^{N}Kg(i)=1 \iff K=\frac{1}{\sum_{i=1}^{N}g(i)}$$
###### Esempio
Sia $Ω = {a, b, c, d}$, ovvero $ω1 = a, ω2 = b, ω3 = c, ω4 = d$, e sia $g(1) = 1, g(2) = 2, g(3) = 4, e g(4) = 1$, allora:
$$K = \frac{1}{\sum_{i=1}^{N}g(i)} = K = \frac{1}{1+2+4+1} = \frac{1}{8}$$
e quindi $p(a) = 1/8, p(b) = 1/4, p(c) = 1/2 p(d) = 1/8$, come nel precedente esempio.

### Spazi di probabilità numerabili
Uno spazio numerabile di probabilità è una terna $(Ω,\mathcal{P} (Ω), \mathbb{P})$ dove:
- $Ω$ è un insieme numerabile
- $\mathcal{P}(Ω)$ è la famiglia delle parti di $Ω$ 
- $\mathbb{P}$ è una probabilità su $(Ω,\mathcal{P} (Ω), \mathbb{P})$, ossia:
	- è una funzione che soddisfa i seguenti assiomi 
		- $\mathbb{P} : \mathcal{P}(Ω) → [0, 1]$
		- $\mathbb{P}(Ω) = 1$( **condizione di normalizzazione**) 
		- Se $E_{n}, n ≥ 1$, sono eventi incompatibili a due a due, ossia $Ei ∩ Ej = ∅$ per ogni $i \ne j$ allora:$$\mathbb{P}=\left( \bigcup_{n \geq 1}E_{n} \right)=\sum_{n \geq 1}\mathbb{P}(E_{n})$$
***Verifica:*** : la verifica è simile a quella del caso finito. 
Sia $c := \mathbb{P}(∅)$ e siano $E_{n} = ∅$, per ogni $n ≥ 1$. Gli eventi $E_{n}$ sono disgiunti a due a due e quindi$$ \begin{align*}
\mathbb{P}=\underbrace{\left( \bigcup_{n \geq 1} E_{n} \right)}_{=\emptyset} &=\sum_{n \geq 1}\mathbb{P}(\underbrace {E_{n}}_{= \emptyset} ) \\ &⇕
\\ \mathbb{P}(\emptyset)&=\sum_{n\geq_{1}}\mathbb{P}(\emptyset)\end{align*}
$$è una serie a termini costanti (in questo caso tutti uguali a $\mathbb{P}(∅)$) è convergente se e solo se la costante è uguale a zero.

##### L’additività numerabile implica l’additività finita
in simboli: se $E_{1}, ..., E_{n}$ sono eventi incompatibili al due a due, ossia:$$\begin{align*}
E_{i} ∩ E_{j} = ∅, \quad \text{per ogni} \quad i \ne j,\quad  i, j ∈ {1, 2, ..., n} \\ \text{allora} \quad \mathbb{P}\left( \bigcup_{i=1}^{n}E_{i} \right)=\sum_{i=1}^{n}\mathbb{P}(E_{i})
\end{align*}$$
***Verifica:*** Poniamo $E_{j} = ∅$ per $j > n$, in modo che $\bigcup_{j>n}E_{j}=\emptyset$ e quindi: $$(\bigcup_{i=1}E_{i}) = (\bigcup_{i=1}E_{i}) \cup (\bigcup_{j>n}E_{j})= \bigcup_{i\geq1}E_{i}$$da cui $$\begin{align*}
\mathbb{P}\left( \bigcup_{i=1}^{n}E_{i} \right) &= \mathbb{P}\left( \bigcup_{i\geq 1}E_{i} \right) \\
= \sum_{i\geq 1}\mathbb{P}(E_{i})&=\sum_{i\geq 1}^{n}\mathbb{P}(E_{i}) + \overbrace{ \sum_{i\geq n}\mathbb{P}(E_{i})}^{=\mathbb{P}(\emptyset)=0}=\sum_{i= 1}^{n}\mathbb{P}(E_{i})
\end{align*}$$Dove nella seconda uguaglianza si usa l’additività numerabile, in quanto si tratta di una successione di eventi disgiunti a due a due: 
- per ogni $i c j, i, j ∈ {1, 2, ..., n}$, si ha $Ei ∩ Ej = ∅$, per ipotesi, 
- per ogni $i \ne j, i, \text{ } j > n$ si ha $Ei ∩ Ej = ∅ ∩ ∅ = ∅$, 
- per ogni $i \ne j,\text{ } i ∈ {1, 2, ..., n}$ e $j > n$ si ha $Ei ∩ Ej = Ei ∩ ∅ = ∅$, (e lo stesso vale per $j ∈ {1, 2, ..., n}$ e $i > n)$

##### Proprietà degli spazi numerabili
Grazie al fatto che l’additività  numerabile implica l’additività finita, tutte le proprietà che abbiamo visto in precedenza continuano a valere anche negli spazi di probabilità numerabili.

###### Estensioni a partizioni numerabili
Sia $\mathcal{H} = \{Hn, \text{ } n ≥ 1\}$ una partizione numerabile (dell’evento certo), ossia $\bigcup_{i \geq 1}H_{i}= Ω$ , $H_{i} ∩ H_{j} = ∅$ per $i \ne j$,allora per ogni evento E si ha $$\mathbb{P}(E)=\sum_{i=1}^{\infty}\mathbb{P}(H_{i}\cap E)$$
***Verifica:*** $$\begin{align*}
\mathbb{P}(E) &= \mathbb{P}(E ∩ Ω) \\ &= \mathbb{P}\left( E \cap \bigcup_{i=1}^{\infty}H_{i} \right) = \mathbb{P}\left(  \bigcup_{i=1}^{\infty}(E \cap H_{i}) \right) \\ &= \sum _{i=1}^{\infty}\mathbb{P}(E\cap H_{i})
\end{align*}$$dove si usa la proprietà distributiva dell’unione rispetto all’intersezione e l’additività numerabile in quanto gli eventi $E ∩ H_{i}$ sono incompatibili a due a due, in quanto sottoinsiemi di eventi incompatibili a due a due.

***AVVERTENZA:*** Per poter usare gli spazi numerabili bisogna avere un minimo di familiarità con le serie e la convergenza di serie.
- In pratica basta avere familiarità con la **serie esponenziale**: $\sum_{k=0}^{\infty}\frac{x^k}{k!}$
- con la serie geometrica, i cui termini sono una progressione: $\sum_{k=0}^{\infty}{k!}$
- le serie delle sue derivate prime e seconde, ossia: $\sum_{k=0}^{\infty}kx^{k-1}$ $\sum_{k=0}^{\infty}{k(k-1)x^{k-2}}$

