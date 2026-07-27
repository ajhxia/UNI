-- ESERCIZIO 1 - mergeSort “iterativo”
-- =============================================================================

-- 1.1 
mergeSortBottomUp :: Ord a => [a] -> [a]
mergeSortBottomUp [] = []
mergeSortBottomUp xs = func1 $ map (\x -> [x]) xs 
  where
    func1 [final_list] = final_list
    func1 lists        = func1 $ func2 lists

    func2 (l1 : l2 : r) = merge l1 l2 : func2 r
    func2 [l1]          = [l1]
    func2 []            = []

    merge [] ys = ys
    merge xs [] = xs
    merge xs@(x:txs) ys@(y:tys)
        | x < y     = x : merge txs ys
        | otherwise = y : merge xs tys

{-
In questa funzione mergeSortBottomUp riceviamo una lista iniziale. 
Tramite 'map', questa viene trasformata in una lista di liste "singleton" 
(liste di un solo elemento) e passata a 'func1'. 
'func1' funge da supervisore ricorsivo: se riceve una singola lista, 
capisce di aver finito e la estrae come risultato finale. 
Se ci sono più liste, le passa a 'func2', che si occupa di scorrerle e 
fonderle a due a due usando 'merge'. Il risultato di questa "passata" 
viene poi ripassato a 'func1', creando un ciclo che dimezza le liste 
a ogni passaggio e termina solo quando ne rimane un'unica ordinata.
-}

-- 1.2 - k è il numero di sequenze crescenti (o già ordinate) presenti all'interno della lista originale.
--- non svolto


-- ESERCIZIO 2 - Alberi & funzionali sugli alberi
-- =============================================================================
data BinTree a = Node a (BinTree a) (BinTree a) | Empty
data BinTree' a = Node' (BinTree' a) (BinTree' a) | Leaf a

-- 2.1 
mapBT :: (a -> b) -> BinTree a -> BinTree b
mapBT _ Empty               = Empty
mapBT f (Node k left right) = Node (f k) (mapBT f left) (mapBT f right)

mapBT' :: (a -> b) -> BinTree' a -> BinTree' b
mapBT' f (Leaf x)           = Leaf (f x)
mapBT' f (Node' left right) = Node' (mapBT' f left) (mapBT' f right)

{-
Nella prima funzione ci troviamo a dover gestire un albero binario dove i dati sono memorizzati nei nodi.
La funzione mapBT prende una funzione funzione f e un albero di tipo BinTree a. Se l'albero è vuoto restituisce 
semplicemente l'albero vuoto. Se invece l'albero è un nodo, applica la funzione f al valore del nodo e chiama ricorsivamente 
mapBT sui figli sinistro e destro, costruendo così un nuovo albero con i valori trasformati.Nella seconda funzione, invece, i dati sono memorizzati nelle foglie. La funzione mapBT' segue una logica simile a mapBT,
ma con una differenza: quando incontra una foglia, applica la funzione f al valore della foglia e restituisce
una nuova foglia con il valore trasformato. Quando incontra un nodo, chiama ricorsivamente mapBT' sui figli sinistro e destro, 
costruendo un nuovo albero con la stessa struttura ma con i valori trasformati nelle foglie.
-}

-- 2.2
pRecBT :: (a -> b -> b -> b) -> b -> BinTree a -> b
pRecBT f z Empty          = z
pRecBT f z (Node x sx dx) = f x (pRecBT f z sx) (pRecBT f z dx)

pRecBT' :: (b -> b -> b) -> (a -> b) -> BinTree' a -> b
pRecBT' g h (Leaf x)      = h x 
pRecBT' g h (Node' sx dx) = g (pRecBT' g h sx) (pRecBT' g h dx)

{- pRecBT è una funzione di ricorsione primitiva per alberi binari. Prende una funzione
f che accetta un valore di tipo a e due valori di tipo b (che rappresentano i risultati 
della ricorsione sui figli sinistro e destro), un valore z che rappresenta 
il caso base per gli alberi vuoti, e un albero di tipo BinTree a. 
Se l'albero è vuoto, restituisce z. Se l'albero è un nodo, applica la 
funzione f al valore del nodo e ai risultati della ricorsione sui figli sinistro
e destro.

pRecBT' è una funzione di ricorsione primitiva per alberi binari con dati nelle 
foglie. Prende una funzione g che accetta due valori di tipo b (che rappresentano 
i risultati della ricorsione sui figli sinistro e destro), una funzione h che accetta un 
valore di tipo a (che rappresenta il caso base per le foglie), e un albero di tipo BinTree' a.
Se l'albero è una foglia, applica la funzione h al valore della foglia. Se l'albero è un nodo, 
applica la funzione g ai risultati della ricorsione sui figli sinistro e destro.
-}

-- 2.3
-- a)
countBT :: BinTree a -> Int
countBT t = pRecBT (\_ sx dx -> 1 + sx + dx) 0 t

countBT' :: BinTree' a -> Int
countBT' t = pRecBT' (\sx dx -> 1 + sx + dx) (const 1) t

-- b)
heightBT :: BinTree a -> Int
heightBT t = pRecBT (\_ sx dx -> 1 + max sx dx) (-1) t

heightBT' :: BinTree' a -> Int
heightBT' t = pRecBT' (\sx dx -> 1 + max sx dx) (const 0) t

-- c) 
combine :: (Int, Int) -> (Int, Int) -> (Int, Int)
combine (altSx, sbilSx) (altDx, sbilDx) = (newAlt, newMaxSbil)
  where
    newAlt     = 1 + max altSx altDx
    sbilLoc    = abs (altSx - altDx)
    newMaxSbil = max sbilLoc (max sbilSx sbilDx)

maxSbilBT :: BinTree a -> Int
maxSbilBT t = snd $ pRecBT (\_ sx dx -> combine sx dx) (-1, 0) t

maxSbilBT' :: BinTree' a -> Int
maxSbilBT' t = snd $ pRecBT' combine (\_ -> (0, 0)) t

-- 2.4 non svolto


-- ESERCIZIO 3 - Minimo Antenato comune
-- =============================================================================

-- 3.1
-- keyLca: Prende queste due liste, le sovrappone, le scorre finché sono uguali e da il punto 
-- esatto in cui le strade si dividono (la chiave dell'antenato comune).
keyLca :: Eq a => [a] -> [a] -> a
keyLca (x:xs) (y:ys)
    | x == y = explore x xs ys  -- x è la radice

{-
explore è una funzione ausiliaria che prende l'ultimo nodo comune visto (ultimoVisto) e le due liste di chiavi (a:as e b:bs).
Esplora le due liste contemporaneamente, confrontando gli elementi.
Se gli elementi sono uguali, aggiorna ultimoVisto e continua a esplorare.
Se gli elementi sono diversi, significa che abbiamo raggiunto il punto in cui i percorsi si 
dividono, quindi restituisce l'ultimo nodo comune visto.
-}
explore :: Eq a => a -> [a] -> [a] -> a
explore ultimoVisto (a:as) (b:bs)
    | a == b    = explore a as bs 
    | otherwise = ultimoVisto 
explore ultimoVisto _ _ = ultimoVisto

-- pathToX tree x e pathToX tree y: Esplorano l'albero in profondità e restituiscono le due mappe del percorso (le liste di chiavi).
pathToX :: Eq a => BinTree a -> a -> [a]
pathToX Empty _ = []
pathToX (Node val sx dx) x
    | val == x             = [val]
    | not (null camminoSx) = val : camminoSx
    | not (null camminoDx) = val : camminoDx
    | otherwise            = []
  where
    camminoSx = pathToX sx x
    camminoDx = pathToX dx x

-- findNode: Prende quella specifica chiave, va di nuovo nell'albero e riporta l'intera struttura 
-- (il BinTree completo di tutti i suoi figli).
findNode :: Eq a => BinTree a -> a -> BinTree a
findNode Empty _ = Empty
findNode (Node val sx dx) x
    | val == x  = Node val sx dx
    | otherwise = 
        case findNode sx x of
            Empty -> findNode dx x  
            node  -> node 

lca :: Eq a => BinTree a -> a -> a -> BinTree a
lca tree u v = findNode tree (keyLca (pathToX tree u) (pathToX tree v))


-- 3.2
lca2 :: Eq a => BinTree a -> a -> a -> BinTree a
lca2 Empty _ _ = Empty
lca2 nodo@(Node val sx dx) u v
    | val == u || val == v = nodo  -- caso in cui uno dei due nodi è l'antenato comune 
    | otherwise =                  -- caso in cui entrambi i nodi sono diversi da val, quindi bisogna esplorare entrambi i rami
        case (risSx, risDx) of
            (Empty, Empty)  -> Empty   -- caso in cui nessuno dei due nodi è presente nei rami
            (Empty, nodoDx) -> nodoDx  -- caso in cui solo il nodo è presente nel ramo destro
            (nodoSx, Empty) -> nodoSx  -- caso in cui solo il nodo è presente nel ramo sinistro
            (_, _)          -> nodo    -- caso in cui entrambi i nodi sono presenti nei rami, quindi val è l'antenato comune
  where
    risSx = lca2 sx u v -- esplora il ramo sinistro
    risDx = lca2 dx u v -- esplora il ramo destro
            
-- 3.3
lcaBST :: Ord a => BinTree a -> a -> a -> BinTree a
lcaBST Empty _ _ = Empty
lcaBST nodo@(Node val sx dx) u v
    | u < val && v < val = lcaBST sx u v
    | u > val && v > val = lcaBST dx u v
    | otherwise          = nodo

-- 3.4 non svolto

-- ESERCIZIO 4 - Derivazioni di programmi
-- =============================================================================
{-
-- la specifica di partenza è la seguente:
scanr f e = map (foldr f e) . tails

-- per la derivazione useremo le definizioni di map, foldr e tails:
map f [] = []
map f (x:xs) = f x : map f xs

foldr f e [] = e
foldr f e (x:xs) = f x (foldr f e xs)

tails [] = [[]]
tails (x:xs) = (x:xs) : tails xs

-- Caso base []
abbiamo che per il lato sinistro della specifica: scanr f e [] = [e] 

-- mentre per il lato destro:
applichiamo la definizione di scanr e della composizione .: map (foldr f e) (tails [])
sappiamo che tails [] = [[]], quindi: map (foldr f e) [[]]
applichiamo la map che agisce su []: [foldr f e []] = [e].
Quindi la specifica è verificata, in quanto entrambi i lati restituiscono [e].
 
-- Caso induttivo (x:xs):
abbiamo che per il lato sinistro della specifica scanr f e (x:xs) 
mentre per il lato destro: map (foldr f e) (tails (x:xs))
applichiamo la definizione di scanr e della composizione .: map (foldr f e) ((x:xs) : tails xs)
applichiamo la map che agisce su (x:xs) e una coda: (foldr f e (x:xs)) : map (foldr f e) (tails xs)
ora notiamo che map (foldr f e) (tails xs) è proprio scanr f e xs, 
quindi possiamo riscrivere la specifica come: (foldr f e (x:xs)) : scanr f e xs
applichiamo la definizione di foldr: f x (foldr f e xs) : scanr f e xs

La chiamata scanr f e xs produce una lista di risultati di foldr f e applicato a tutte le sottoliste di xs, inclusa la lista vuota. 
il primo elemento di scanr f e xs è proprio foldr f e xs. Quindi, chiamo la lista risultato qs.
Essendo il risultato scanr, qs non sarà mai vuota perché contiene almeno il risultato di foldr f e [] che è [e].
Posso riscvirere rs@(r:_) dove r è la testa. Sappiamo per certo che r = foldr f e xs, 
quindi possiamo riscrivere la specifica come: f x r : rs dove rs@(r:_) = scanr f e xs.
-}

scanr' :: (a -> b -> b) -> b -> [a] -> [b]
scanr' f e [] = [e]
scanr' f e (x:xs) = f x q : qs
  where 
    qs@(q:_) = scanr' f e xs

main :: IO ()
main = do putStrLn $ "Alessia Cassetta 2113909"