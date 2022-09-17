import java.util.Scanner;

public class Brush_iv 
{

    static Scanner in = new Scanner(System.in);

    static int testCases, n, k, w;

    static long x[], y[], dp[];

    static long solve(int mask, point a[]) 
    {

        if(dp[mask] != -1) return dp[mask];

        int count = 0;

        for(int i = 0; i < n; ++i) 
        {

            if(!check(mask, i)) ++count;

        }

        if(count == 1 || count == 0) 
        {

            return dp[mask] = count;

        }

        long ret = Long.MAX_VALUE;

        for(int i = 0; i < n; ++i) 
        {

            if(!check(mask, i)) 
            {

                for(int j = 0; j < n; ++j) 
                {

                    if(i != j && !check(mask, j)) 
                    {

                        int temp = mask;

                        for(int k = 0; k < n; ++k) 
                        {

                            if(line(a[i], a[j], a[k])) 
                            {

                                temp = set(temp, k);

                            }

                        }

                        ret = Math.min(ret, 1 + solve(temp, a)); 

                    }

                    //break;

                }

                break;

            }

        }

        return dp[mask] = ret;

    }

    static void solve_testCase(int t) 
    {

        System.out.print("Case " + t + ": ");

        long ans = 0L;

        point a[] = new point[n];

        for(int i = 0; i < n; ++i) 
        {

            a[i] = new point(x[i], y[i]);

        }

        for(int i = 0; i < dp.length; ++i) dp[i] = -1L;

        ans = solve(0, a);

        System.out.println(ans);

    }

    public static void main(String [] amit) 
    {

        testCases = in.nextInt();

        for(int t = 0; t < testCases; ++t)
        {

            n = in.nextInt();
            //w = in.nextInt();
            //k = in.nextInt();

            x = new long[n];
            y = new long[n];

            for(int i = 0; i < n; ++i)
            {

                x[i] = in.nextLong();
                y[i] = in.nextLong();

            }

            dp = new long[(1<<16)+10];

            solve_testCase(t + 1); 

        }  

    }

    static class point 
    {

        long x, y;

        public point(long x, long y) 
        {

            this.x = x;
            this.y = y;

        }

    }
    
    static boolean line(point a, point b, point c) 
    {

        return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) == 0L;
    }

    static boolean check(int n, int pos) 
    {

        return (n & (1 << pos)) > 0;

    }

    static int set(int n, int pos) 
    {

        return (n | (1 << pos));
        
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