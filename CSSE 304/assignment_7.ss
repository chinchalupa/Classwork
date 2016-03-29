(define vector-append-list
	(lambda (v lst)
		(define len (+ (vector-length v) (length lst)))
		(define vect (make-vector len 't))

		(define newvec (copy-vector-elements vect v 0))
		(copy-ls-elements newvec lst (vector-length v))
		;lst
	)
)

(define copy-vector-elements
	(lambda (newvec v count)
		(if (< count (vector-length v))
			(begin
				(vector-set! newvec count (vector-ref v count))
				(copy-vector-elements newvec v (+ 1 count))
			)
			newvec
		)
	)
)

(define copy-ls-elements
	(lambda (newvec ls count)
		(if (null? ls)
			newvec
			(begin
				(vector-set! newvec count (car ls))
				(copy-ls-elements newvec (cdr ls) (+ 1 count))))))

(define qsort
	(lambda (pred ls)
		(if (null? ls)
			ls
			(append  (qsort pred (filter-in pred ls (car ls))) (list (car ls)) (qsort pred (cdr (filter-out pred ls (car ls)))))
		)
	)
)

(define filter-in
	(lambda (pred ls val)
		(if (null? ls)
			'()
			(if (pred (car ls) val)
				(cons (car ls) (filter-in pred (cdr ls) val))
				(filter-in pred (cdr ls) val)
				)
			)
		)
	)

(define filter-out
	(lambda (pred ls val)
		(if (null? ls)
			'()
			(if (pred (car ls) val)
				(filter-out pred (cdr ls) val)
				(cons (car ls) (filter-out pred (cdr ls) val))

				)
			)
		)
	)


(define reverse-it
	(lambda (ls)
		(new-reverse ls '())
	)
)

(define new-reverse
	(lambda (ls accum)
		(if (null? ls)
			accum
			(new-reverse (cdr ls) (cons (car ls) accum))
		)
	)
)

(define connected?
	(lambda (g)
		(if (null? g) #t)
		(subset? (map car g) (has-a-connection? g (caar g) (map car g) (list)))
		)
	)

(define has-a-connection?
	(lambda (g t unexplored explored)
		(let ((next (filter (lambda (x) (not (member x explored)))
							(get-node g t))))
			(if (null? next)
				(cons t explored)
				(apply append (map (lambda (x) (has-a-connection? g x unexplored (cons t explored))) next))
			)
		)
	)
)

; Unused. Replaced with let statement.
(define get-next
	(lambda (x explored)
		(not (member x explored))
	)
)

(define get-node
	(lambda (g name)
		(if (null? g)
			g
			(if (equal? name (caar g))
				(cadar g)
				(get-node (cdr g) name)
			)
		)
	)
)

(define subset?
	(lambda (l1 l2)
		(or (null? l1)
			(and (member (car l1) l2)
				 (subset? (cdr l1) l2)
			 )
		)
	)
)

(define empty-BST
	(lambda ()
		'()
	)
)

(define empty-BST?
	(lambda (obj)
		(null? obj)
	)
)

(define BST-insert
	(lambda (num bst)
		(if (empty-BST? bst)
			(list num '(() ()))
			(if (eq? num (BST-element bst))
				bst
				(if (> num (car bst))
					(list (BST-element bst) (list (BST-left bst) (BST-insert num (BST-right bst))))
					(list (BST-element bst) (list (BST-insert num (BST-left bst)) (BST-right bst)))
				)
			)
		)
	)
)

(define BST-inorder
	(lambda (bst)
		(if (null? bst)
			'()
			(append (BST-inorder (BST-left bst)) (list (BST-element bst)) (BST-inorder (BST-right bst)))
		)
	)
)

(define BST-insert-nodes
	(lambda (bst ls)
		(if (null? ls)
			bst
			(BST-insert-nodes (BST-insert (car ls) bst) (cdr ls))
		)
	)
)

(define BST-element
	(lambda (bst)
		(car bst)
	)
)

(define BST-left
	(lambda (bst)
		(caadr bst)
	)
)

(define BST-right
	(lambda (bst)
		(cadadr bst)
	)
)

(define BST-contains?
	(lambda (bst num)
		(if (null? bst)
			#f
			(if (eq? (BST-element bst) num)
				#t
				(begin
					(or (BST-contains? (BST-left bst) num) (BST-contains? (BST-right bst) num))
				)
			)
		)
	)
)

(define BST?
	(lambda (bst)
		(if (null? bst)
			#t
			(if (list? bst)
				(if (> (length bst) 2)
					(and (BST? (cadr bst)) (BST? (caddr bst)) (number? (car bst)))
					#f
				)
				#f
			)
		)
	)
)

(define map-by-position
	(lambda (fn-list arg-list)
		(if (null? fn-list)
			'()
			(cons ((car fn-list) (car arg-list)) (map-by-position (cdr fn-list) (cdr arg-list)))
		)
	)
)

(define bt-leaf-sum
	(lambda (ls)
		(if (number? ls)
			ls
			(+ (bt-leaf-sum (cadr ls)) (bt-leaf-sum (caddr ls)))
		)
	)
)

(define bt-inorder-list
	(lambda (ls)
		(if (number? ls)
			'()
			(append (bt-inorder-list (cadr ls)) (list (car ls)) (bt-inorder-list (caddr ls)))
		)
	)
)

(define bt-max
	(lambda (ls)
		(if (number? ls)
			ls
			(max (bt-max (caddr ls)) (bt-max (cadr ls)))
		)
	)
)

(define BST-left
	(lambda (bst)
		(cadr bst)
	)
)

(define BST-right
	(lambda (bst)
		(caddr bst)
	)
)

(define bt-max-interior
	(lambda (T)
		(caddr (bt-max-interior-helper T))
	)
)

(define bt-max-interior-helper
	(lambda (T)
		(let* ([left-node (if (number? (BST-left T)) (BST-left T) (bt-max-interior-helper (BST-left T)))]
			   [right-node (if (number? (BST-right T)) (BST-right T) (bt-max-interior-helper (BST-right T)))])
			(if (number? left-node)
				(if (number? right-node)
					(list (BST-element T) (+ left-node right-node) (BST-element T) (+ left-node right-node))
					(maxhelper (list (BST-element T) (+ left-node (cadr right-node))) right-node)
					)
				(if (number? right-node)
					(maxhelper (list (BST-element T) (+ (cadr left-node) right-node)) left-node)
					(maxhelper (list (BST-element T) (+ (cadr left-node) (cadr right-node)))
							   (maxhelper (list (caddr left-node) (cadddr left-node))
										  (maxhelper (list (car left-node) (cadr left-node)) right-node)))
				)
			)
		)
	)
)

(define maxhelper
	(lambda (pair1 pair2)
		(let ([node (cadr pair1)]
			  [current-max (cadddr pair2)])
			(if (>= node current-max)
				(list (car pair1) node (car pair1) node)
				(list (car pair1) node (caddr pair2) current-max)
			)
		)
	)
)