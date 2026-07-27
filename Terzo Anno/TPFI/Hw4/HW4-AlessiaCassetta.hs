import Data.List (find)
import Control.Monad.State

-- ESERCIZIO 1 
-- =============================================================================
{-  
    Serve a leggere una serie di numeri da input e restituirli come lista.
-}
readNumber :: Int -> IO [Int]
readNumber 0 = return []
readNumber k = do
    x  <- readLn                -- legge il primo numero e lo chiama 'x'
    xs <- readNumber (k - 1)    -- richiama ricorsivamente la funzione per leggere i restanti (k-1) numeri e li chiama 'xs'
    return (x:xs)               -- li unisce e li restituisce nell'IO


{-
    Serve a sommare una lista di numeri dato un intero m che rappresenta quanti numeri dobbiamo leggere. 
     - Legge n numeri da input usando la funzione readNumber
     - Somma i numeri letti e stampa il risultato
-}
adder :: IO ()
adder = do
    n <- readLn                -- legge il primo numero (quanti ne dobbiamo inserire)
    num <- readNumber n
    let sumN = sum num         -- somma i numeri letti
    print sumN                 -- stampa la somma


-- ESERCIZIO 2
-- =============================================================================

{-  
    La funzione divisori prende un intero n e restituisce una lista dei suoi divisori propri, ovvero tutti i numeri interi positivi che dividono n senza lasciare resto, escluso n stesso. 
    Per fare questo, utilizza una list comprehension che itera su tutti i numeri da 1 a n/2 (incluso) e include solo quelli che soddisfano la condizione di essere un divisore di n. 
    Ci fermiamo alla metà per non fare calcoli inutili, visto che nessun divisore proprio può essere più grande della metà del numero
-}
divider :: Int -> [Int]
divider n = [x | x <- [1..(n `div` 2)], n `mod` x == 0] 

{-
    La funzione sottoinsiemi genera tutti i sottoinsiemi di una lista data. 
    - Se la lista è vuota, restituisce una lista contenente solo la lista vuota.
    - Altrimenti, prende il primo elemento (x) e genera i sottoinsiemi del resto della lista (xs). 
      Poi combina questi sottoinsiemi con quelli che includono x, usando l'operatore <*> per applicare entrambe le funzioni (inserire x o non inserirlo tramite l'identità) a ciascun sottoinsieme generato da xs.
-}
subset :: [a] -> [[a]]
subset [] = [[]]
subset (x:xs) = [(x:), id] <*> subset xs

{-
    La funzione semiperfetto verifica se un numero n è semiperfetto, ovvero se esiste un sottoinsieme dei suoi divisori propri che somma esattamente a n. 
    - Prima calcola i divisori propri di n usando la funzione divisori.
    - Poi genera tutti i sottoinsiemi di questi divisori usando la funzione sottoinsiemi.
    - Infine, cerca tra questi sottoinsiemi uno la cui somma sia esattamente n. Se ne trova uno, restituisce True; altrimenti, restituisce False.
-}
semiPerfect :: Int -> Bool
semiPerfect n = 
    let divPropri = divider n
        allSubset = subset divPropri
        risultato = find (\s -> sum s == n) allSubset
    in case risultato of
        Just _  -> True   -- Esiste almeno un sottoinsieme che dia somma pari ad n e dato che non mi interessa quale sia, basta sapere che esiste. 
        Nothing -> False  -- Se la ricerca ha restituito Nothing, significa che nessun sottoinsieme somma a n. Il numero non è semi-perfetto, quindi restituiamo False.


-- ESERCIZIO 2
-- =============================================================================
{-
    Si definisce un albero binario parametrico, che può essere vuoto (Empty) o un nodo (Node) che contiene un valore di tipo a e due sottoalberi (sinistro e destro). 
    Deriving Show e Eq permette di stampare l'albero e confrontarlo con altri alberi per uguaglianza.
-}
data BinTree a = Empty | Node a (BinTree a) (BinTree a)
  deriving (Show, Eq)

{-
    State a (a, [a]) rappresenta un'operazione che, dato uno stato di tipo a, restituisce una coppia composta da un valore di tipo a e una lista di valori di tipo a. 
    La funzione explore prende un albero binario di numeri e restituisce una lista di tutti i valori presenti nell'albero, insieme alla somma di questi valori. 
    Tramite get e put, mantiene una somma cumulativa dei valori incontrati lungo il percorso dall'albero. 
    - Se l'albero è vuoto, restituisce una somma di 0 e una lista vuota.
    - Se l'albero non è vuoto, aggiorna la somma cumulativa con il valore del nodo corrente, esplora ricorsivamente i sottoalberi sinistro e destro, e poi calcola la somma totale sotto l'albero corrente. 
      Se la somma totale sotto l'albero è uguale alla somma cumulativa (upSum), allora il nodo corrente è considerato "equilibrato" e il suo valore viene aggiunto alla lista dei nodi equilibrati. 
      Infine, restituisce la somma totale sotto l'albero e la lista di tutti i nodi equilibrati trovati.

-}
explore :: (Eq a, Num a) => BinTree a -> State a (a, [a]) 
explore Empty = return (0, [])  -- Se l'albero è vuoto, restituisce una somma di 0 e una lista vuota.
explore (Node val left right) = do
    upSum <- get
    put (upSum + val) -- aggiorna la somma cumulativa con il valore del nodo corrente
    (sumLeft, leftNodes) <- explore left -- esplora ricorsivamente il sottoalbero sinistro e ottiene la somma e i nodi equilibrati trovati lì
    put (upSum + val) 
    (sumRight, rightNodes) <- explore right -- esplora ricorsivamente il sottoalbero destro
    let sumUnderTree = val + sumLeft + sumRight 
        -- se il nodo è equilibrato, lo mettiamo in una lista, altrimenti lista vuota
        nodeA = if upSum == sumUnderTree then [val] else []
        -- Uniamo tutte le liste trovate
        allNodes = nodeA ++ leftNodes ++ rightNodes
    return (sumUnderTree, allNodes)

equilibrati :: (Eq a, Num a) => BinTree a -> [a]
equilibrati tree = snd (evalState (explore tree) 0)

main :: IO ()
main = do putStrLn $ "Alessia Cassetta 2113909"