// find palinmdrome for a given number

import java.util.Scanner;

public class GPT {
    private static class DisjointSetElement {
        private DisjointSetElement parent;    
        private int rank;
        private int size;

        public DisjointSetElement() {
            this.parent = this;
            this.rank = 0;
            this.size = 1;
        }
        
        public DisjointSetElement find() {
            if (this.parent != this) {
                this.parent = this.parent.find();
            }
            return this.parent;
        }

        public void union(DisjointSetElement other) {
            DisjointSetElement root1 = this.find();
            DisjointSetElement root2 = other.find();
            if (root1 == root2) {
                return;
            }
            if (root1.rank < root2.rank) {
                root1.parent = root2;
                root2.size += root1.size;
            } else if (root1.rank > root2.rank) {
                root2.parent = root1;
                root1.size += root2.size;
            } else {
                root2.parent = root1;
                root1.rank++;
                root1.size += root2.size;
            }
        }
    }
}