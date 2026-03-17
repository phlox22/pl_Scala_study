import scala.collection.mutable

// [공통 구조] 과제 가이드의 힌트를 바탕으로 정의한 가변 객체 [cite: 55]
class Pair(var car: Any = null, var cdr: Any = null)

object PairAssignment {

  // --- Exercise 2: 고유한 쌍 개수 세기 ---
  // 보조 자료구조(Set)를 사용하여 중복 카운팅을 방지함 [cite: 44]
  def countDistinctPairs(x: Any): Int = {
    val visited = mutable.Set[Pair]()
    def count(item: Any): Int = item match {
      case p: Pair =>
        if (visited.contains(p)) 0
        else {
          visited.add(p)
          1 + count(p.car) + count(p.cdr)
        }
      case _ => 0
    }
    count(x)
  }

  // --- Exercise 3: 순환 탐지 ---
  // cdr을 따라가며 무한 루프 여부를 판별함 [cite: 47, 48]
  def hasCycle(x: Any): Boolean = {
    val visited = mutable.Set[Pair]()
    def check(item: Any): Boolean = item match {
      case p: Pair =>
        if (visited.contains(p)) true
        else {
          visited.add(p)
          check(p.cdr)
        }
      case _ => false
    }
    check(x)
  }

  // --- Exercise 4: 상수 공간 순환 탐지 ---
  // O(1) 공간만을 사용하는 토끼와 거북이 알고리즘 [cite: 51]
  def hasCycleOptimized(x: Any): Boolean = {
    var slow: Any = x
    var fast: Any = x

    while (fast != null) {
      fast match {
        case f: Pair =>
          val next = f.cdr
          next match {
            case n: Pair =>
              fast = n.cdr
              slow = slow.asInstanceOf[Pair].cdr
            case _ => return false
          }
        case _ => return false
      }
      if (slow == fast) return true
    }
    false
  }

  // --- 실행 테스트 ---
  def main(args: Array[String]): Unit = {
    println("=== Exercise 2: Distinct Count ===")
    val p3 = new Pair('c', null)
    val p2 = new Pair(p3, p3)
    val p1 = new Pair(p2, p2)
    println(s"Actual Pairs: 3, Counted: ${countDistinctPairs(p1)}")

    println("\n=== Exercise 3 & 4: Cycle Detection ===")
    val c3 = new Pair('z', null)
    val c2 = new Pair('y', c3)
    val c1 = new Pair('x', c2)
    c3.cdr = c1 // 순환 생성 [cite: 48]
    println(s"Ex 3 (Set): ${hasCycle(c1)}")
    println(s"Ex 4 (O(1)): ${hasCycleOptimized(c1)}")
  }
}