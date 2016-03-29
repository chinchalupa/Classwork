(define vec-length
	(lambda (ls)
		(if (null? ls)
			0
			(sqrt (square-list ls))
		)
	)
)

(define square-list
	(lambda (ls)
		(if (null? ls)
			0
			(+ (* (car ls) (car ls)) (square-list (cdr ls)))
		)
	)
)

(define distance
	(lambda (ls1 ls2)
		(vec-length (make-vec-from-points ls1 ls2))
	)
)

(define make-vec-from-points
	(lambda (ls1 ls2)
		(if (null? ls1)
			'()
			(cons (- (car ls2) (car ls1)) (make-vec-from-points (cdr ls1) (cdr ls2)))
		)
	)
)

(define nearest-point
	(lambda (p1 plist)
		(if (null? (cdr plist))
			(car plist)
			(if (<= (distance p1 (car plist)) (distance p1 (nearest-point p1 (cdr plist))))
				(car plist)
				(nearest-point p1 (cdr plist))
			)
		)
	)
)

(define union
	(lambda (ls1 ls2)
		(if (null? ls2)
			ls1
			(if (member (car ls2) ls1)
				(union ls1 (cdr ls2))
				(cons (car ls2) (union ls1 (cdr ls2)))
			)
		)
	)
)

(define intersection
	(lambda (ls1 ls2)
		(if (null? ls2)
			'()
			(if (member (car ls2) ls1)
				(cons (car ls2) (intersection ls1 (cdr ls2)))
				(intersection ls1 (cdr ls2))
			)
		)
	)
)

(define subset?
	(lambda (ls1 ls2)
		(eq? (length (union ls1 ls2)) (length ls2))
	)
)

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


(define relation?
	(lambda (ls)
		(if (set? ls)
			(relation-helper ls)
			#f
		)
	)
)

(define relation-helper
	(lambda (ls)
		(if (and (null? ls))
			#t
			(if (and (list? ls) (list? (car ls)))
				
					(and (eq? (length (car ls)) 2) (relation? (cdr ls)))
					#f
			)
		)
	)
)

(define domain
	(lambda (ls)
		(define dom (map car ls))
		(remove-duplicates dom)
	)
)

(define remove-duplicates
	(lambda (ls)
		(if (null? ls)
			'()
			(if (member (car ls) (cdr ls))
				(remove-duplicates (cdr ls))
				(cons (car ls) (remove-duplicates (cdr ls)))
			)
		)
	)
)

(define reflexive?
	(lambda (ls)
		(define elements (remove-duplicates (append (map car ls) (map cadr ls))))
		(iterate-elements ls elements)
	)
)

(define iterate-elements
	(lambda (ls elements)
		(if (null? elements)
			#t
			(has-pair ls (car elements))
		)
	)
)

(define has-pair
	(lambda (ls element)
		(if (null? ls)
			#f
			(or (and (eq? element (caar ls)) (eq? element (cadar ls))) (has-pair (cdr ls) element))
		)
	)
)

(define hailstone-step-count
	(lambda (number)
		(if (eq? 1 (max 1 number))
			0
			(if (eq? 0 (modulo number 2))
				(+ 1 (hailstone-step-count (/ number 2)))
				(+ 1 (hailstone-step-count (+ (* 3 number) 1)))
			)
		)		
	)
)







