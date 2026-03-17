import scala.collection.mutable

// 가변 객체 정의 (과제 가이드 참고)
//class Pair(var car: Any = null, var cdr: Any = null)

@main def runEx2(): Unit = {
  def countDistinctPairs(x: Any): Int = {
    // 방문한 쌍들을 기록해 두는 보조 자료구조 (Set)
    val visited = mutable.Set[Pair]()

    def count(item: Any): Int = item match {
      case p: Pair =>
        if (visited.contains(p)) 0 // 이미 방문했다면 0 반환
        else {
          visited.add(p) // 처음 방문하는 쌍 기록
          1 + count(p.car) + count(p.cdr)
        }
      case _ => 0
    }
    count(x)
  }

  // 테스트: 결과가 7이 나오던 중복 구조
  val p3 = new Pair('c', null)
  val p2 = new Pair(p3, p3)
  val p1 = new Pair(p2, p2)

  println(s"Exercise 2 - 고유 쌍 개수: ${countDistinctPairs(p1)}") // 결과: 3
}