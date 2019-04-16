package test;

import java.util.Arrays;
import java.util.Random;

public class BB {
	static Random random = new Random();  
	static {  
	random.setSeed(System.currentTimeMillis());  
	}  
	  static long xRandom(long min, long max) {  
	        return sqrt(nextLong(sqr(max - min)));  
	    }  
	public static void main(String[] args) {
		long k=0;
		long[] generate = generate(10000,20,60000,1);
		for (long l : generate) {
			k+=l;
		}
		System.out.println(Arrays.toString(generate));
		System.out.println(k);
	} 
	 public static long[] generate(long total, int count, long max, long min) {  
	        long[] result = new long[count];  
	        long average = total / count;  
	        long a = average - min;  
	        long b = max - min;  
	        //  
	        // 这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。  
	        // 这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。  
	        long range1 = sqr(average - min);  
	        long range2 = sqr(max - average);  
	        for (int i = 0; i < result.length; i++) {  
	            // 因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。  
	            // 当随机数>平均值，则产生小红包  
	            // 当随机数<平均值，则产生大红包  
	            if (nextLong(min, max) > average) {  
	                // 在平均线上减钱  
	                // long temp = min + sqrt(nextLong(range1));  
	                long temp = min + xRandom(min, average);  
	                result[i] = temp;  
	                total -= temp;  
	            } else {  
	                // 在平均线上加钱  
	                // long temp = max - sqrt(nextLong(range2));  
	                long temp = max - xRandom(average, max);  
	                result[i] = temp;  
	                total -= temp;  
	            }  
	        }  
	        // 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。  
	        while (total > 0) {  
	            for (int i = 0; i < result.length; i++) {  
	                if (total > 0 && result[i] < max) {  
	                    result[i]++;  
	                    total--;  
	                }  
	            }  
	        }  
	        // 如果钱是负数了，还得从已生成的小红包中抽取回来  
	        while (total < 0) {  
	            for (int i = 0; i < result.length; i++) {  
	                if (total < 0 && result[i] > min) {  
	                    result[i]--;  
	                    total++;  
	                }  
	            }  
	        }  
	        return result;  
	    }  
	 
	 static long sqrt(long n) {  
	        // 改进为查表？  
	        return (long) Math.sqrt(n);  
	    }  
	  
	    static long sqr(long n) {  
	        // 查表快，还是直接算快？  
	        return n * n;  
	    }  
	  
	    static long nextLong(long n) {  
	        return random.nextInt((int) n);  
	    }  
	  
	    static long nextLong(long min, long max) {  
	        return random.nextInt((int) (max - min + 1)) + min;  
	    }  
	    
}
