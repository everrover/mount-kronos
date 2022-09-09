# A few notes

### Comparator - comparing method

- Static method
- Impl for Int, Double, String and Long available
- Impl present for reversing, handling nulls and adding multiple orderings

```java
// note the implementation of comparators
PriorityQueue<Room> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.room));

// note uage of streams over PQ
pq.stream()
.sorted((r1, r2) -> {
    if (r1.meetingCnt == r2.meetingCnt) {
        return Integer.compare(r1.roomNumber, r2.roomNumber);
    }
    return Integer.compare(r2.meetingCnt, r1.meetingCnt);
}).findFirst().get().room;

// https://www.baeldung.com/java-8-comparator-comparing
```