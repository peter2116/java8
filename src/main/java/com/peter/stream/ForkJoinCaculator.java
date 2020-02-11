package com.peter.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/11
 * @Description:
 * @modifier
 */
public class ForkJoinCaculator extends RecursiveTask<Long> {

	private Long start;
	private Long end;

	private final static Long ThresHold = 10000L;

	public ForkJoinCaculator(Long start, Long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		Long sum = 0L;
		if(end - start <= ThresHold) {
			for(long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		}else {
			long mid = (start + end )/ 2;
			ForkJoinCaculator left = new ForkJoinCaculator(start,mid);
			ForkJoinCaculator right = new ForkJoinCaculator(mid + 1,end);
			left.fork();
			right.fork();
			return left.join() + right.join();
		}
	}
}
