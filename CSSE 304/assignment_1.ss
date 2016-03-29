(define Fahrenheit->Celsius(
	lambda (temp)
		(/ (* 5 (- temp 32)) 9)))
	
(define interval-contains? (
	lambda (pair number)
		(if (>= number (car pair))
			(<= number (car (cdr pair)))
			#f)
	)
)

(define interval-intersects? (
		lambda (pair matchingPair)
			(if (and (>= (car (cdr pair)) (car matchingPair))
			(>= (car(cdr matchingPair)) (car pair)))
			#t
			#f)
	)
)

(define interval-union (
		lambda (pair matchingPair)
		
			(define x1 (car pair))
			(define x2 (car matchingPair))
			(define y1 (car (cdr pair)))
			(define y2 (car (cdr matchingPair)))
			(define minimum (min x1 x2))
			(define maximum (max y1 y2))

			
			(if (interval-intersects? pair matchingPair)
				(list (list minimum maximum))
				(list pair matchingPair)
			)
	)
)

(define divisible-by-7? (
	lambda (number)
		(if (eq? (modulo number 7) 0)
		#t
		#f)
	)
)

(define ends-with-7? (
	lambda (number)
		(eq? (modulo number 10) 7)
	)
)

(define 1st (
		lambda (lizt)
			(car lizt)
	)
)

(define 2nd (
		lambda (lizt)
			(car (cdr lizt))
	)
)

(define 3rd (
		lambda (lizt)
			(car (cddr lizt))
	)
)
