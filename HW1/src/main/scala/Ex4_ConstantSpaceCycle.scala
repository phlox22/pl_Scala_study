//class Pair(var car: Any = null, var cdr: Any = null)

@main def runEx4(): Unit = {
  // 상수 공간(O(1))만을 사용하는 순환 탐지 알고리즘
  def hasCycleOptimized(x: Any): Boolean = {
    var slow: Any = x // 거북이 (느린 포인터)
    var fast: Any = x // 토끼 (빠른 포인터)

    while (fast != null) {
      // 토끼는 한 번에 두 칸씩 이동 (힌트: 포인터 트릭 사용)
      fast match {
        case f: Pair =>
          val next = f.cdr
          next match {
            case n: Pair =>
              fast = n.cdr
              // 거북이는 한 번에 한 칸씩 이동
              slow = slow.asInstanceOf[Pair].cdr
            case _ => return false // 리스트 끝 도달
          }
        case _ => return false
      }

      // 토끼와 거북이가 만나면 순환이 존재함
      if (slow == fast) return true
    }
    false
  }

  // 테스트: 순환 구조
  val p3 = new Pair('c', null)
  val p2 = new Pair('b', p3)
  val p1 = new Pair('a', p2)
  p3.cdr = p1

  println(s"Exercise 4 - 상수 공간 순환 탐지: ${hasCycleOptimized(p1)}") // 결과: true
}