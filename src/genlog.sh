MU=(4 5 6 7 8 9 10)
LAMBDA=( 5 6 7 8 9 10 11)
DUREE=(10 100 1000 10000 100000 1000000 10000000 100000000)

for mu in ${MU[@]}
do
  for l in ${LAMBDA[@]}
  do
    for d in ${DUREE[@]}
    do
      if [ ${mu} -lt ${l} ]
      then
        java MM1 ${mu} ${l} ${d} 42
      fi
    done
  done
done
