import Data.List (sort)

-- ESERCIZIO 1
-- =============================================================================

------ 1.1 Definire il funzionale zipWith f xs ys senza decomporre liste, 
-- ma usando un’espressione che contenga zapp, f ed eventualmente xs e ys.

zApp :: [a -> b] -> [a] -> [b]
zApp (f:fs) (x:xs) = f x : zApp fs xs  -- Applica la testa e fa la ricorsione sulla coda
zApp _      _      = []                -- Caso base: se una lista si svuota, termina

zipWithCustom :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWithCustom f xs ys = zApp (map f xs) ys

-- SPIEGAZIONE DEI PASSAGGI:
-- L'espressione (map f xs) prende la funzione binaria 'f' e la applica parzialmente a ogni 
-- elemento di 'xs'. Questo genera una lista di funzioni unarie del tipo [b -> c] Passiamo
-- questa nuova lista di funzioni e la seconda lista di valori 'ys' a zApp. zApp si occupa
-- di consumare le due liste, applicando le funzioni agli elementi di 'ys'.


------ 1.2 Abbiamo visto che zipWith è più generale di zip. Tuttavia si può definire 
-- zipWith f xs ys usando zip e un paio di altri funzionali visti a lezione.

zipWithCustom2 f xs ys = map (uncurry f) (zip xs ys)

-- SPIEGAZIONE DEI PASSAGGI:
-- (zip xs ys): Prende le due liste in ingresso 'xs' e 'ys' e le fonde in una singola lista 
-- di tuple (coppie). 
-- (uncurry f): Prende la funzione binaria originale 'f' (che si aspetta i parametri separati) 
-- e la trasforma in una funzione capace di accettare e spacchettare una singola tupla alla volta.
-- map: Prende la funzione appena adattata da uncurry e la applica a tappeto su ogni singola 
-- tupla della lista creata da zip. Il risultato finale è la lista dei valori calcolati.


------ 1.3 Deﬁnire il funzionale map f xs senza decomporre xs, ma usando un’espressione 
-- che contenga foldr, f e xs. Fare lo stesso usando foldl.

-- map definita usando foldr
mapFoldr :: (a -> b) -> [a] -> [b]
mapFoldr f xs = foldr (\x acc -> f x : acc) [] xs

-- map definita usando foldl
mapFoldl :: (a -> b) -> [a] -> [b]
mapFoldl f xs = foldl (\acc x -> acc ++ [f x]) [] xs

-- SPIEGAZIONE DEI PASSAGGI (foldl):
-- foldl scorre la lista da sinistra verso destra.
-- L'accumulatore iniziale è la lista vuota []. La lambda (\acc x -> acc ++ [f x]) prende 
-- l'accumulatore corrente 'acc', calcola 'f x', lo mette in una lista singola '[f x]' e lo 
-- appende in coda.


------ 1.4 Argomentare brevemente sul perchè non sia possibile definire foldl e foldr usando map

-- Non è possibile definire foldl e foldr usando map poichè essa applica una funzione 
-- unaria (a -> b) a ogni elemento in modo indipendente, mentre i fold richiedono una 
-- funzione binaria (b -> a -> b o a -> b -> b) per poter gestire il passaggio di stato 
-- (l'accumulatore).


-- ESERCIZIO 2
-- =============================================================================

------ 2.1 Dare la deﬁnizione di myTakeWhile e myDropWhile

myTakeWhile :: (a -> Bool) -> [a] -> [a]
myTakeWhile _ [] = []                   -- caso base: lista vuota
myTakeWhile p (x:xs)
    | p x       = x : myTakeWhile p xs  -- se la condizione è vera, tiene 'x' e continua
    | otherwise = []                    -- se è falsa, si ferma (restituisce lista vuota)

myDropWhile :: (a -> Bool) -> [a] -> [a]
myDropWhile _ [] = []                   -- caso base: lista vuota
myDropWhile p (x:xs)
    | p x       = myDropWhile p xs      -- se la condizione è vera, scarta 'x' e continua
    | otherwise = x : xs                -- se è falsa, si ferma e mantiene tutto il resto della lista


------ 2.2 scrivere una funzione ricorsiva myRemoveDupsOrd che rimuove i duplicati da una lista 
-- ordinata xs di lunghezza n in tempo O(n).

-- si assicura che gli elementi di questa lista si possano confrontare per capire se sono uguali
myRemoveDupsOrd :: Eq a => [a] -> [a]   
myRemoveDupsOrd [] = []
myRemoveDupsOrd (x:xs) = x : myRemoveDupsOrd (myDropWhile (== x) xs)


------ 2.3 scrivere una funzione myRemoveDups che rimuove i duplicati da una
-- qualsiasi lista xs di lunghezza n in tempo O(n log n), preservando l’ordine
-- originale delle prime occorrenze degli elementi rimasti

myRemoveDups :: Ord a => [a] -> [a]
myRemoveDups xs = map snd $ sort $ map (\(v, i) -> (i, v)) (filterDups $ sort $ zip xs [0..])

-- in O(n)
filterDups :: Eq a => [(a, Int)] -> [(a, Int)]
filterDups [] = []
filterDups (t:ts) = t : filterDups (myDropWhile (\x -> fst x == fst t) ts)

-- SPIEGAZIONE DEI PASSAGGI: 
-- Il processo inizia dall'estrema destra grazie ai simboli '$': questi operatori indicano a 
-- Haskell di valutare l'intera espressione alla loro destra prima di passare il risultato alla 
-- funzione di sinistra. Per prima cosa, viene eseguito lo 'zip xs [0..]' che crea una lista di 
-- tuple dove ogni elemento di 'xs' è accoppiato a un indice progressivo. Successivamente, la 
-- lista viene data in pasto a 'sort' in cui, essendo tuple, vengono ordinate per valore. In 
-- questo modo, tutti i duplicati finiscono in posizioni adiacenti.
-- A questo punto 'filterDups' scorre la lista ordinata e, usando 'dropWhile', elimina i duplicati 
-- consecutivi. Poiché la lista era stata ordinata, ne rimane solo una copia per ogni valore. 
-- Usiamo 'map (\(v, i) -> (i, v))' per scambiare di posto gli elementi della tupla. L'indice 
-- originale torna in prima posizione e il valore in seconda.
-- Viene applicato un secondo 'sort' in cui, avendo l'indice in prima posizione, riordina la 
-- lista secondo la sequenza originale.
-- Infine, 'map snd' pulisce scartando gli indici e restituisce la lista finale contenente solo 
-- i valori originali, senza duplicati e nell'ordine corretto.


-- ESERCIZIO 3
-- =============================================================================

------ 3.1 Scrivere una funzione prefissi :: [a] → [[a]] che ritorna tutti i segmenti 
-- iniziali di una lista.

prefissi :: [a] -> [[a]]
prefissi [] = [[]]
prefissi (x:xs) = [] : map (x:) (prefissi xs)

-- SPIEGAZIONE DEI PASSAGGI: 
-- inseriamo la lista vuota [] come aprimo elemento del risultato. Il resto della struttura 
-- è costruito applicando una 'map' che prende l'elemento corrente 'x' e lo aggiunge in testa 
-- (x:) a ciascuna delle sotto-liste (i prefissi) generate dalla chiamata ricorsiva sul resto 
-- della lista (xs).


------ 3.2 Senza preoccuparsi dell’efficienza, ma usando i funzionali prefissi, suffissi e altri 
-- funzionali dello standard Prelude, scrivere una funzione segSommaS :: (Num a) ⇒ [a] → a → [[a]] 
-- che data una lista numerica xs e un valore s restituisce tutti i segmenti (cio`e sottoliste di 
-- elementi consecutivi) di xs di somma s

segSommaS :: (Num a, Eq a) => [a] -> a -> [[a]]
segSommaS xs s = filter (\seg -> sum seg == s) (concat $ map (prefissi) (suffissi xs))


------ 3.3 Scrivere una funzione sublSommaS :: (Num a) ⇒ [a] → a → [[a]] che data una 
-- lista numerica e un valore s restituisce tutte le sottoliste (anche di elementi non 
-- consecutivi) di somma s.

subl [] = [[]]
subl [x] = [[x]]
subl (x:xs) = rest ++ map (x:) rest
  where rest = subl xs

sublSommaS ys n = filter (\seg -> sum seg == n) (subl ys)


-- ESERCIZIO 4 (Facoltativo)
-- =============================================================================

recP :: (Integer → a → a) → a → Integer → a
recP f v n = snd $ for (\(i, acc) -> (i + 1, f (i + 1) acc)) (0, v) n 

-- SPIEGAZIONE DEI PASSAGGI:
-- La funzione recP prende tre argomenti: una funzione 'f' che accetta un intero e un 
-- accumulatore, un valore iniziale 'v' e un numero 'n' che indica quante volte eseguire la 
-- funzione. La funzione 'for' è un costrutto iterativo che simula un ciclo for. In questo 
-- caso, inizia con una tupla (0, v) dove 0 è l'indice iniziale e 'v' è l'accumulatore iniziale. 
-- Ad ogni iterazione, la funzione lambda (\(i, acc) -> (i + 1, f (i + 1) acc)) viene chiamata, 
-- incrementando l'indice 'i' e aggiornando l'accumulatore 'acc' con il risultato di 
-- 'f (i + 1) acc'. Dopo 'n' iterazioni, 'for' restituisce la tupla finale, da cui estraiamo 
-- solo l'accumulatore con 'snd'. 


-- ESERCIZIO 5 (Facoltativo) - Non svolto
-- =============================================================================

main :: IO ()
main = do putStrLn $ "Alessia Cassetta 2113909"