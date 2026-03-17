import scala.collection.mutable

//class Pair(var car: Any = null, var cdr: Any = null)

@main def runEx3(): Unit = {
  def hasCycle(x: Any): Boolean = {
    val visited = mutable.Set[Pair]() // 방문한 노드 저장용 

    def check(item: Any): Boolean = item match {
      case p: Pair =>
        if (visited.contains(p)) true // 이미 방문한 노드 재방문 시 순환 있음 [cite: 48]
        else {
          visited.add(p)
          check(p.cdr) // 계속해서 cdr(꼬리)을 따라감 [cite: 48]
        }
      case _ => false // 리스트의 끝에 도달하면 순환 없음 [cite: 48]
    }
    check(x)
  }

  // 테스트: 순환 구조 만들기
  val p3 = new Pair('c', null)
  val p2 = new Pair('b', p3)
  val p1 = new Pair('a', p2)
  p3.cdr = p1 // p3 -> p1 순환 발생

  println(s"Exercise 3 - 순환 여부: ${hasCycle(p1)}") // 결과: true
}