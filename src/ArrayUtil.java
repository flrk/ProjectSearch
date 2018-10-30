public class ArrayUtil {
    public static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }


    public static int[] concat(int[] a, int[] b){
        int[] ab = new int[a.length + b.length];
        for(int i = 0; i < ab.length; i++){
            if(i < a.length){
                ab[i] = a[i];
            }else{
                ab[i]  = b[i - a.length];
            }
        }
        return ab;
    }

}