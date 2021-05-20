public class ArgSum{
	public static void main(String[] args) {
		int len = args.length;
		int i = 0;
		int sum = 0;
		while(i < len){
			sum += Integer.parseInt(args[i]);
			i ++;
		}
		System.out.println(sum);
	}
}
