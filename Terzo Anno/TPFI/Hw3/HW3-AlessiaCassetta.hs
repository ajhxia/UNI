import Data.List (unfoldr)

-- ESERCIZIO 1 - insomnia
-- =============================================================================
insomnia :: [Char]
insomnia = concat (map (\x -> show x ++ " sheep ") [1..])


-- ESERCIZIO 2 - tartaglia
-- =============================================================================
tartaglia :: [[Int]]
tartaglia = iterate (\xs -> zipWith (+) (0:xs) (xs ++ [0])) [1]


-- ESERCIZIO 3 - numeri fortunati
-- =============================================================================
-- ricorsivo
lucky :: [Int]
lucky = 1 : loop 1 [1, 3..]
  where
    loop n xs =
        let step = xs !! n
            xsNext = map snd (filter (\(i, _) -> i `mod` step /= 0) (zip [1..] xs))
        in step : loop (n + 1) xsNext

-- co-ricorsivo
luckyCorec :: [Int]
luckyCorec = 1 : unfoldr f (1, [1, 3..])
  where
    f (n, xs) =
        let step = xs !! n
            xsNext = map snd (filter (\(i, _) -> i `mod` step /= 0) (zip [1..] xs))
        in Just (step, (n + 1, xsNext))
{- Just serve a gestire tutte le situazioni in caso in cui un valore potrebbe esistere, ma potrebbe anche non esserci (o un'operazione potrebbe fallire). 
-}

main :: IO ()
main = do putStrLn $ "Alessia Cassetta 2113909"