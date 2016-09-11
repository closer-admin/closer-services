package services.convertion

trait ConversionFormat [A, B] {

  implicit def apply(o: A):B
  implicit def apply(seq: Seq[A]):Seq[B] = seq.map(apply(_))
  implicit def apply(set: Set[A]):Set[B] = set.map(apply(_))
  implicit def apply(opt: Option[A]):Option[B] = opt.map(apply(_))

  implicit def unapply(o: B):A
  implicit def unapply(seq: Seq[B]):Seq[A] = seq.map(unapply(_))
  implicit def unapply(set: Set[B]):Set[A] = set.map(unapply(_))
  implicit def unapply(opt: Option[B]):Option[A] = opt.map(unapply(_))

}
