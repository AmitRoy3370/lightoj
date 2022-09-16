import java.util.Scanner;

public class Brush_iii 
{

    static Scanner in = new Scanner(System.in);

    static int testCases, n, k, w;

    static long x[], y[];

    /*
    *              2 + max(0, 0) = 2
    *            (0, 0)
    *                        0 + max(0, 0) = 0
    *  (3, 1)               (1, 0)
    *    0             0                0
    *                (1, 1)           (2, 0)
    *                                            0 + max(0, 0)
    *                        (3, 0)            (2, 1)
    *                          0
    *                                 (3, 1)            (2, 2) 
    *                                   0                  0
    */

    static long solve(long dp[][], int position, int moves) 
    {

        if(position >= n || moves >= k) return 0L;
        if(dp[position][moves] != -1) return dp[position][moves];

        int i, count = 0;

        for(i = 0; i < n - position; ++i) 
        {

            if(y[position] + w < y[position + i]) 
            {

                break;

            }

            ++count;

        }

        long ans = Math.max(count + solve(dp, position + i, moves + 1), solve(dp, position + 1, moves));
        dp[position][moves] = ans;

        return dp[position][moves];

    }

    static void solve(int t) 
    {

        System.out.print("Case " + t + ": ");

        long ans = 0L;

        sort(y, 0, n - 1);

        long dp[][] = new long[n + 1][n + 1];

        for(int i = 0; i <= n; ++i) 
        {

            for(int j = 0; j <= n; ++j) dp[i][j] = -1L;

        }

        ans = solve(dp, 0, 0);

        System.out.println(ans);

    }

    public static void main(String [] amit) 
    {

        testCases = in.nextInt();

        for(int t = 0; t < testCases; ++t)
        {

            n = in.nextInt();
            w = in.nextInt();
            k = in.nextInt();

            x = new long[n];
            y = new long[n];

            for(int i = 0; i < n; ++i)
            {

                x[i] = in.nextLong();
                y[i] = in.nextLong();

            }

            solve(t + 1); 

        }  

    }

    static void merge(long a[], int left, int right, int mid) 
    {
 
        int n1 = mid - left + 1, n2 = right - mid;
 
        long L[] = new long[n1];
 
        long R[] = new long[n2];
 
        for (int i = 0; i < n1; i++) 
        {
 
            L[i] = a[left + i];
 
        }
 
        for (int i = 0; i < n2; i++) 
        {
 
            R[i] = a[mid + 1 + i];
 
        }
 
        int i = 0, j = 0, k1 = left;
 
        while (i < n1 && j < n2) 
        {
 
            if (L[i] <= R[j]) 
            {
 
                a[k1] = L[i];
 
                i++;
 
            } else 
            {
 
                a[k1] = R[j];
 
                j++;
 
            }
 
            k1++;
 
        }
 
        while (i < n1) 
        {
 
            a[k1] = L[i];
 
            i++;
 
            k1++;
 
        }
 
        while (j < n2) 
        {
 
            a[k1] = R[j];
 
            j++;
            k1++;
 
        }
 
    }
 
    static void sort(long a[], int left, int right) 
    {
 
        if (left >= right) 
        {
 
            return;
 
        }
 
        int mid = (left + right) / 2;
 
        sort(a, left, mid);
 
        sort(a, mid + 1, right);
 
        merge(a, left, right, mid);
 
    }

}