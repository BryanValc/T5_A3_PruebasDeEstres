import java.util.Arrays;

class GeneracionNumeros{
	public static int[] generarNumerosAleatorios(int cnt){
		int[] ret = new int[cnt];
		for (int i = 0; i < ret.length; i++) {
			ret[i]=(int)(Math.random()*cnt);
		}
	    return ret;
	}
}

class MetodosOrdenamiento{
	static long resultados[][][]=new long[4][3][4];

	static class Intercalacion{
		
		public static void complementoIntercalacion(int numeros[]){
			int aux;
			for (int i = 1; i < numeros.length; i++) {
				aux=numeros[i];
				for (int j=i-1; j>=0 && numeros[j]>aux ; j--) {
					numeros[j+1]=numeros[j];
					numeros[j]=aux;
				}
			}
		}
		
		public static void ordenacionIntercalacion(int nums[],int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			
			int[] arregloA,arregloB;
			
			if (numeros.length%2==0) {
				arregloA=new int[numeros.length/2];
				arregloB=new int[numeros.length/2];
			}else {
				arregloA=new int[numeros.length/2];
				arregloB=new int[(numeros.length/2)+1];
			}
			for (int i = 0; i < numeros.length; i++) {
				if (i<numeros.length/2) {
					arregloA[i]=numeros[i];
				}else {
					arregloB[i-arregloA.length]=numeros[i];
				}
			}
			complementoIntercalacion(arregloA);
			complementoIntercalacion(arregloB);
			
			long ini = System.nanoTime();
			
			int arregloC[] = new int[arregloA.length+arregloB.length];
			
			int i,k,j;
			recorridos+=1;
			for(i=j=k=0; i<arregloA.length && j<arregloB.length; k++){
				comparaciones+=1;
				intercambios+=1;
				if(arregloA[i]<arregloB[j]) {
					arregloC[k] = arregloA[i];
					i++;
				}else {
					arregloC[k] = arregloB[j];
					j++;
				}
			}
			recorridos+=1;
			for(;i<arregloA.length; i++,k++) {
				arregloC[k] = arregloA[i];
				intercambios+=1;
			}
			recorridos+=1;
			for(;j<arregloB.length; j++,k++) {
				arregloC[k] = arregloB[j];
				intercambios+=1;
			}
			
			long fin = System.nanoTime();
			
			resultados[np][0][0]=comparaciones;
			resultados[np][0][1]=intercambios;
			resultados[np][0][2]=recorridos;
			resultados[np][0][3]=fin-ini;
		}
		
	}//class Intercalacion
	
	
	
	
	
	
}

public class PruebaPruebasDeEstres {

	public static void main(String[] args) {
		
		String metodos[]= {"Intercalacion","Mezcla directo","Mezcla natural"};
		for (int i = 0; i < 4; i++) {
			int nums[]=GeneracionNumeros.generarNumerosAleatorios((int) (1000*Math.pow(10, i)));
			MetodosOrdenamiento.Intercalacion.ordenacionIntercalacion(nums, i);
			
			System.out.println("========================prueba de "+nums.length+" numeros========================\nmetodo		|comparaciones	|intercambios	|recorridos	|runtime	|");
			for (int j = 0; j < 3; j++) {
				System.out.print(metodos[j]+"	|");
				for (int k = 0; k < 4; k++) {
					System.out.print(MetodosOrdenamiento.resultados[i][j][k]+(MetodosOrdenamiento.resultados[i][j][k]<1000000 ? "		|":"	|"));
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}

}
