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
	static long resultados[][][]=new long[4][8][4];
	static class Burbuja {
		
		public static void ordenacionBurbuja1(int nums[],int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			
			long ini = System.nanoTime();
			recorridos+=1;
			for (int i = 1; i < numeros.length; i++) {
				recorridos+=1;
				for (int j = 0; j <= numeros.length-i-1; j++) {
					comparaciones+=1;
					if(numeros[j]>numeros[j+1]) {
						intercambios+=1;
						int aux = numeros[j];
						numeros[j]=numeros[j+1];
						numeros[j+1]=aux;
					}
				}
			}
			long fin = System.nanoTime();
			resultados[np][0][0]=comparaciones;
			resultados[np][0][1]=intercambios;
			resultados[np][0][2]=recorridos;
			resultados[np][0][3]=fin-ini;
			
		}
		public static void ordenacionBurbuja2(int nums[],int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			
			int i =1;
			boolean ordenado=false;
			long ini = System.nanoTime();
			recorridos+=1;
			while (i<numeros.length) {
				ordenado=true;
				recorridos+=1;
				for (int j = 0; j < numeros.length-i; j++) {
					comparaciones+=1;
					if(numeros[j]>numeros[j+1]) {
						intercambios+=1;
						ordenado=false;
						int aux = numeros[j];
						numeros[j]=numeros[j+1];
						numeros[j+1]=aux;
					}
				}
				i+=1;
			}
			long fin = System.nanoTime();
			resultados[np][1][0]=comparaciones;
			resultados[np][1][1]=intercambios;
			resultados[np][1][2]=recorridos;
			resultados[np][1][3]=fin-ini;
			
		}
		public static void ordenacionBurbuja3(int nums[],int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			
			int i =1;
			long ini = System.nanoTime();
			recorridos+=1;
			do{
				boolean ordenado=true;
				recorridos+=1;
				for (int j = 0; j < numeros.length-i; j++) {
					comparaciones+=1;
					if(numeros[j]>numeros[j+1]) {
						intercambios+=1;
						ordenado=false;
						int aux = numeros[j];
						numeros[j]=numeros[j+1];
						numeros[j+1]=aux;
					}
				}
				i+=1;
			}while (i<numeros.length);
			long fin = System.nanoTime();
			resultados[np][2][0]=comparaciones;
			resultados[np][2][1]=intercambios;
			resultados[np][2][2]=recorridos;
			resultados[np][2][3]=fin-ini;
			

		}
	
	}//class Burbuja
	
	
	static class Insercion {
		
		public static void ordenacionInsercion(int nums[],int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			int aux;
			
			long ini = System.nanoTime();
			recorridos+=1;
			for (int i = 1; i < numeros.length; i++) {
				aux=numeros[i];
				comparaciones+=1;
				recorridos+=1;
				for (int j=i-1; j>=0 && numeros[j]>aux ; j--) {
					comparaciones+=1;
					numeros[j+1]=numeros[j];
					numeros[j]=aux;
					intercambios+=1;
				}
				
			}
			long fin = System.nanoTime();
			resultados[np][3][0]=comparaciones;
			resultados[np][3][1]=intercambios;
			resultados[np][3][2]=recorridos;
			resultados[np][3][3]=fin-ini;
			
		}
		
	}//class Insercion
	
	
	static class Seleccion{
		
		public static void ordenacionSeleccion(int[] nums,int np) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			
			long ini = System.nanoTime();
			recorridos+=1;
			for(int i = 0; i < numeros.length - 1; i++) {
				recorridos+=1;
				for(int j = i + 1; j < numeros.length; j++) {
					comparaciones+=1;
					if(numeros[i] > numeros[j]) {
						intercambios+=1;
						int orden = numeros[i];
						numeros[i] = numeros[j];
						numeros[j] = orden;
					}
				}
			}
			long fin = System.nanoTime();
			resultados[np][4][0]=comparaciones;
			resultados[np][4][1]=intercambios;
			resultados[np][4][2]=recorridos;
			resultados[np][4][3]=fin-ini;
			
		}
		
	}//class Seleccion
	
	
	static class Quicksort{
		static long comparaciones=0;
		static long intercambios=0;
		static long recorridos=0;
		
        static public int[] quicksort(int[] numeros,int izq,int der) {
            int pivote = numeros[izq];
            int i = izq, j = der;
            int aux;
            recorridos+=1;
            while(i<j) {
            	comparaciones+=1;
            	recorridos+=1;
                while(numeros[i]<=pivote && i<j) i++;
                comparaciones+=1;
                recorridos+=1;
                while(numeros[j]>pivote)j--;
                if(i<j) {
                	intercambios+=1;
                    aux = numeros[i];
                    numeros[i]=numeros[j];
                    numeros[j] = aux;
                }
            }
            intercambios+=1;
            numeros[izq]=numeros[j];
            numeros[j]=pivote;
            if(izq<j-1)
                quicksort(numeros,izq,j-1);
            if(j+1<der)
                quicksort(numeros, j+1, der);
            return numeros;
        }
        
        public static void llamadaQuicksort(int nums[],int np) {
        	int numeros[]=nums.clone();
        	
        	long ini = System.nanoTime();
        	quicksort(numeros,0,numeros.length-1);
			long fin = System.nanoTime();
			
			resultados[np][5][0]=comparaciones;
			resultados[np][5][1]=intercambios;
			resultados[np][5][2]=recorridos;
			resultados[np][5][3]=fin-ini;
			
			comparaciones=intercambios=recorridos=0;
        	
        }
    
        
    }//class Quicksort
	
	
	static class Shellsort{
		
		public static void shellsort(int[] nums,int np) {
			int numeros[]=nums.clone();
			int intervalo;
			long comparaciones=0,intercambios=0,recorridos=0;
			intervalo = numeros.length/2;
			long ini = System.nanoTime();
			recorridos+=1;
			while(intervalo>0) {
				recorridos+=1;
				for(int i = intervalo; i<numeros.length; i++) {
					int j=i-intervalo;
					recorridos+=1;
					while(j>=0) {
						int k=j+intervalo;
						comparaciones+=1;
						if(numeros[j] <= numeros[k]) {
							j-=1;
						}else {
							int aux = numeros[j];
							intercambios+=1;
							numeros[j] = numeros[k];
							numeros[k] = aux;
							j-=intervalo;
						}
					}
				}
				intervalo=intervalo/2;
			}
			long fin = System.nanoTime();
			resultados[np][6][0]=comparaciones;
			resultados[np][6][1]=intercambios;
			resultados[np][6][2]=recorridos;
			resultados[np][6][3]=fin-ini;
			
		}
		
	}//class Shellsort 
	
	
	static class Radix{
		
		public static void radix(int[]nums,int npr) {
			int numeros[]=nums.clone();
			long comparaciones=0,intercambios=0,recorridos=0;
			long ini = System.nanoTime();
			if(numeros.length == 0)
		          return;
		          int[][] np = new int[numeros.length][2];
		          int[] q = new int[0x100];
		          int i,j,k,l,f = 0;
		          recorridos+=1;
		          for(k=0;k<4;k++) {
		        	  recorridos+=1;
		             for(i=0;i<(np.length-1);i++)
		             np[i][1] = i+1;
		             np[i][1] = -1;
		             recorridos+=1;
		             for(i=0;i<q.length;i++)
		             q[i] = -1;
		             recorridos+=1;
		             for(f=i=0;i<numeros.length;i++) {
		                j = ((0xFF<<(k<<3))&numeros[i])>>(k<<3);
		                if(q[j] == -1)
		                l = q[j] = f;
		             else {
		                l = q[j];
		                recorridos+=1;
		                while(np[l][1] != -1)
		                l = np[l][1];
		                np[l][1] = f;
		                l = np[l][1];
		                intercambios+=1;
		             }
		             f = np[f][1];
		             np[l][0] = numeros[i];
		             np[l][1] = -1;
		             intercambios+=1;
		          }
		          for(l=q[i=j=0];i<0x100;i++)
		          for(l=q[i];l!=-1;l=np[l][1])
		        	  numeros[j++] = np[l][0];
		       }//for
		          long fin = System.nanoTime();
		          resultados[npr][7][0]=comparaciones;
		          resultados[npr][7][1]=intercambios;
		          resultados[npr][7][2]=recorridos;
		          resultados[npr][7][3]=fin-ini;
		}
		
	}//class Radix
	
	
}

public class PruebaPruebasDeEstres {

	public static void main(String[] args) {
		
		String metodos[]= {"Burbuja1","Burbuja2","Burbuja3","Insercion","Seleccion","Quicksort","Shellsort","Radix	"};
		for (int i = 0; i < 4; i++) {
			int nums[]=GeneracionNumeros.generarNumerosAleatorios((int) (1000*Math.pow(10, i)));
			MetodosOrdenamiento.Burbuja.ordenacionBurbuja1(nums, i);
			System.out.println("Burbuja1	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Burbuja.ordenacionBurbuja2(nums, i);
			System.out.println("Burbuja2	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Burbuja.ordenacionBurbuja3(nums, i);
			System.out.println("Burbuja3	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Insercion.ordenacionInsercion(nums, i);
			System.out.println("Insercion	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Seleccion.ordenacionSeleccion(nums, i);
			System.out.println("Seleccion	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Quicksort.llamadaQuicksort(nums, i);
			System.out.println("Quicksort	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Shellsort.shellsort(nums, i);
			System.out.println("Shellsort	"+nums.length+" numeros terminada...");
			MetodosOrdenamiento.Radix.radix(nums, i);
			System.out.println("Radix		"+nums.length+" numeros terminada...");
			System.out.println("========================prueba de "+nums.length+" numeros========================\nmetodo		|comparaciones	|intercambios	|recorridos	|runtime	|");
			for (int j = 0; j < 8; j++) {
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
