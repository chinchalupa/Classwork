
(define interval-intersects? (
		lambda (pair matchingPair)
			(if (and (>= (cadr pair) (car matchingPair))
			(>= (cadr matchingPair) (car pair)))
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

(define (interval-list-helper sorted tosort)
	(if (null? tosort)
		sorted
		(let ([newpairs (interval-union (car sorted) (car tosort))])
			(if (= (length newpairs) 2)
				(interval-list-helper (append (list (cadr newpairs)) sorted) (cdr tosort))
				(interval-list-helper (append (list (car newpairs)) (remove (car sorted) sorted) ) (cdr tosort))
			)
		)
	)
) 

(define (minimize-interval-list ls) 
	(let ([sorted (sort (lambda (tosort1 tosort2)
		(< (car tosort1)
		(car tosort2))) ls )])
		
		(interval-list-helper (list (car sorted)) (cdr sorted))
	)
)

(define exists?
	(lambda (pred ls)
		(ormap pred ls)
	)
)

(define list-index
	(lambda (pred ls)
		(if (exists? pred ls)
			(if (pred (car ls))
				0
				(+ 1 (list-index pred (cdr ls)))
			)
			#f
		)
	)
)

(define pascal-triangle
	(lambda (number)
		(if (> 0 number)
			(list)
			(cons (build-pascal-row number 0) (pascal-triangle (- number 1)))
		)
	)
)

(define build-pascal-row
	(lambda (high iter)
		(if (< high iter)
			'()
			(cons (combinatorial high iter) (build-pascal-row high (+ iter 1)))
		)
	)
)

(define fact
	(lambda (n)
		(if (eq? n 0)
			1
			(* n (fact (- n 1)))
		)
	)
)

(define combinatorial
	(lambda (n k)
		(quotient (fact n) (* (fact (- n k)) (fact k)))
	)
)

(define product
	(lambda (ls mult)
		(if (not (null? ls))
			(append (create-chain (car ls) mult) (product (cdr ls) mult))
			'()
		)
	)
)

(define create-chain
	(lambda (val mult)
		(if (null? mult)
			'()
			(cons (list val (car mult)) (create-chain val (cdr mult)))
		)
	)
)