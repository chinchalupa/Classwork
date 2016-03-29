(define set?
	(lambda (ls)
	
		(if (list? ls)
		
			(if (null? ls)
				#t
				(if (member (car ls) (cdr ls))
					#f
					(set? (cdr ls))
				
				)
			)	
			#f
		)
	)
)

(define multi-set?
	(lambda (ls)
		
		
		(if (and (andmap list? ls) (set? (map car ls)))
			(multi-set-helper ls)
			#f
		)
	)
)

(define multi-set-helper
	(lambda (ls)
	
		
		(if (null? ls)
			#t
			(if (and (not (vector? (car ls)))
				(not (number? (car (car ls))))
				(number? (cadr (car ls)))
				(> (cadr (car ls)) 0))
				(multi-set-helper (cdr ls))
				#f
			)
		)
	)
)

(define ms-size
	(lambda (ls)
		(if (multi-set? ls)
			(get-sizes ls)
			#f
		)
	)
)

(define get-sizes
	(lambda (ls)
		(if (null? ls)
			0	
			(+ (cadar ls) (get-sizes (cdr ls)))
		)
	)
)

(define matrix-ref
	(lambda (ls n m)
		(list-ref (list-ref ls n) m)
	)
)

(define matrix? 
	(lambda (ls)
		
		(if (and (list? ls) (andmap list? ls) (not (null? (car ls))))
			(check-same-sizes ls)
			#f
		)
	)
)


(define check-same-sizes
	(lambda (ls)
		(if (null? (cdr ls))
			#t
			(if (equal? (length (car ls)) (length (cadr ls)))
				(check-same-sizes (cdr ls))
				#f
			)
		)
	)
)

(define matrix-transpose
	(lambda (ls)
		(if (matrix? (map cdr ls))
			(cons(map car ls) (matrix-transpose (map cdr ls)))
			(list(map car ls))
		)
	)
)

(define last
	(lambda (ls)
		(list-ref ls (- (length ls) 1))
	)
)

(define all-but-last
	(lambda (ls)
		(if (null? (cdr ls))
			'()
			(cons (car ls) (all-but-last (cdr ls)))
		)
	)
)
