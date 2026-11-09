# Combinatorics Cheat Sheet

| Type                                                        | Order Matters? | Repetition Allowed? |                                      Formula                                       | Real-Life Example                                                                                           | Java Code Logic                                                                                    |                                                              Example values                                                              |
|:------------------------------------------------------------|:--------------:|:-------------------:|:----------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------:|
| **Permutations (without repetition) -> Anagrams**           |    **Yes**     |       **No**        |               $$P_n = n!$$ subset of arrangements if n = k $$A_n^k$$               | Arranging **all** 5 books on a shelf, or shuffling a full deck of cards.                                    | `n` factorial. Total ways to rearrange all `n` distinct items.                                     |                                             A,B,C $$P_3 = 6 $$ ABC, ACB, BAC, BCA, CAB, CBA                                              |
| **Permutations with Repetition**                            |    **Yes**     |   **Yes (Fixed)**   | $$P_n^{n_1, n_2, \dots, n_k} = \frac{n!}{n_1! \cdot n_2! \cdot \dots \cdot n_k!}$$ | Unscrambling or making words from the letters of "**MAMA**" or "**BANANA**".                                | Rearranging a multi-set (where you have a fixed pool of items with duplicate values).              |                                      M,A,M,A $$P_4^{2,2} = 6 $$ AAMM, AMAM, AMMA, MAAM, MMAA, MAMA                                       |
| **Arrangements (without repetition)**<br>*(k-Permutations)* |    **Yes**     |       **No**        |                           $$A_n^k = \frac{n!}{(n-k)!}$$                            | Awarding **Gold, Silver, and Bronze** medals to 3 runners out of 10 participants.                           | Picking and ordering $k$ distinct items out of $n$ available.                                      |                              A,B,C $$A_3^2 = 6 $$ AB,AC,BA, BC,CA,CB; $$A_3^3=6$$ ABC,ACB,BAC, BCA,CAB,CBA                               |
| **Arrangements with Repetition -> Cartesian Product**       |    **Yes**     | **Yes (Unlimited)** |                            $$\widetilde{A}_n^k = n^k$$                             | Creating a **4-digit ATM PIN** (`0000`-`9999`) or setting up a multi-digit bicycle lock combo.              | Generating PIN codes, passwords, or consecutive dice roll outcomes.                                | A,B,C $$A_3^3 = 27$$ AAA,AAB,AAC, ABA,ABB,ABC, ACA,ACB,ACC, BAA,BAB,BAC, BBA,BBB,BBC, BCA,BCB,BCC, CAA,CAB,CAC, CBA,CBB,CBC, CCA,CCB,CCC |
| **Combinations (without repetition)**                       |     **No**     |       **No**        |                   $$C_n^k = \binom{n}{k} = \frac{n!}{k!(n-k)!}$$                   | Winning the **Lottery** (picking 6 winning numbers from 49), or selecting a 3-person jury out of 12.        | Selecting subsets, groups, or teams of size $k$ where internal hierarchy doesn't matter.           |                                            A,B,C $$C_3^2 = 3$$ AB,AC,BC; $$C_3^3 = 1$$ A,B,C                                             |
| **Combinations with Repetition**                            |     **No**     | **Yes (Unlimited)** |        $$\widetilde{C}_n^k = \binom{n+k-1}{k} = \frac{(n+k-1)!}{k!(n-1)!}$$        | Buying **5 scoops of ice cream** at a shop that offers 3 flavors (e.g., getting 3 chocolate and 2 vanilla). | "Stars and Bars" problem. Distribution of $k$ identical item choices into $n$ distinct categories. |                                     A,B,C $$C_3^3 = 10$$ AAA,AAB,AAC, ABB,ABC,ACC, BBB,BBC,BCC, CCC                                      |

---

## Java Implementations


```java
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Combinatorics {

    // Helper: Factorial using BigInteger to prevent integer overflow
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * 1. Permutations (without repetition)
     * Rearranging all n distinct items.
     * Example: Rearranging {A, B, C} -> P(3) = 3! = 6
     */
    public static BigInteger permutations(int n) {
        return factorial(n);
    }

    /**
     * 2. Permutations with Repetition (Multi-set Permutations)
     * Rearranging a group of items where some items are identical.
     * Example: Rearranging "MAMA" (n=4, M=2, A=2) -> 4! / (2! * 2!) = 6
     * @param counts Array containing the frequency of each unique item.
     */
    public static BigInteger permutationsWithRepetition(int n, int[] counts) {
        BigInteger denominator = BigInteger.ONE;
        for (int count : counts) {
            denominator = denominator.multiply(factorial(count));
        }
        return factorial(n).divide(denominator);
    }

    /**
     * 3. Arrangements without Repetition (k-Permutations)
     * Choosing k distinct items out of n, order matters.
     * Example: Picking a President and VP from 5 people -> A(5, 2) = 5! / 3! = 20
     */
    public static BigInteger arrangements(int n, int k) {
        if (k < 0 || k > n) return BigInteger.ZERO;
        return factorial(n).divide(factorial(n - k));
    }

    /**
     * 4. Arrangements with Repetition
     * Choosing k items out of n where items can be reused, order matters.
     * Example: 3-digit PIN using digits 0-9 -> A_rep(10, 3) = 10^3 = 1000
     */
    public static BigInteger arrangementsWithRepetition(int n, int k) {
        if (n < 0 || k < 0) return BigInteger.ZERO;
        return BigInteger.valueOf(n).pow(k);
    }

    /**
     * 5. Combinations (without repetition)
     * Choosing k distinct items out of n, order does NOT matter.
     * Example: Choosing a team of 3 from 10 people -> C(10, 3) = 120
     */
    public static BigInteger combinations(int n, int k) {
        if (k < 0 || k > n) return BigInteger.ZERO;
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        return numerator.divide(denominator);
    }

    /**
     * 6. Combinations with Repetition
     * Choosing k items out of n where items can be reused, order does NOT matter.
     * Example: Buying 5 scoops of ice cream when 3 flavors are available -> C_rep(3, 5) = C(3+5-1, 5) = C(7, 5) = 21
     */
    public static BigInteger combinationsWithRepetition(int n, int k) {
        if (n <= 0 || k < 0) return BigInteger.ZERO;
        return combinations(n + k - 1, k);
    }

    // Quick verification
    public static void main(String[] args) {
        System.out.println("P(5): " + permutations(5)); // 120
        System.out.println("P_rep(MAMA): " + permutationsWithRepetition(4, new int[]{2, 2})); // 6
        System.out.println("A(5, 2): " + arrangements(5, 2)); // 20
        System.out.println("A_rep(10, 3): " + arrangementsWithRepetition(10, 3)); // 1000
        System.out.println("C(10, 3): " + combinations(10, 3)); // 120
        System.out.println("C_rep(3, 5): " + combinationsWithRepetition(3, 5)); // 21
    }
}
```
