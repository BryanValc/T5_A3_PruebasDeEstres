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
	
	
	static class MezclaDirecto{
		static long comparaciones=0;
		static long intercambios=0;
		static long recorridos=0;
		
		public static int [] ordenamientoMezclaDirecto(int arreglo[]) {
			int i,j,k;
			if(arreglo.length>1) {
				int numElementosIzq=arreglo.length/2;
				int numElmentosDer=arreglo.length-numElementosIzq;
				
				int arregloIzquierdo[]=new int[numElementosIzq];
				int arregloDerecha[]=new int[numElmentosDer];
				recorridos+=1;
				for(i=0;i<numElementosIzq;i++) {
					arregloIzquierdo[i]=arreglo[i];
					intercambios+=1;
				}
				i=0;
				recorridos+=1;
				for(i=numElementosIzq;i<numElementosIzq+numElmentosDer;i++) {
					arregloDerecha[i-numElementosIzq]=arreglo[i];
					intercambios+=1;
				}
				
				arregloIzquierdo=ordenamientoMezclaDirecto(arregloIzquierdo);
				arregloDerecha=ordenamientoMezclaDirecto(arregloDerecha);
				i=j=k=0;
				recorridos+=1;
				while(arregloIzquierdo.length!=j && arregloDerecha.length!=k) {
					comparaciones+=1;
					intercambios+=1;
					if(arregloIzquierdo[j]<arregloDerecha[k]) {
						arreglo[i]=arregloIzquierdo[j];
						i++;
						j++;
					}else {
						arreglo[i]=arregloDerecha[k];
						i++;
						k++;
					}
				}
				recorridos+=1;
				while(arregloIzquierdo.length!=j) {
					intercambios+=1;
					arreglo[i]=arregloIzquierdo[j];
					i++;
					j++;
				}
				recorridos+=1;
				while(arregloDerecha.length!=k) {
					intercambios+=1;
					arreglo[i]=arregloDerecha[k];
					i++;
					k++;
				}
			}
			
			return arreglo;
		}
		
		public static void llamadaOrdenamientoMezclaDirecto(int nums[],int np) {
			int numeros[]=nums.clone();
			
			long ini = System.nanoTime();
			ordenamientoMezclaDirecto(numeros);
			long fin = System.nanoTime();
			
			resultados[np][1][0]=comparaciones;
			resultados[np][1][1]=intercambios;
			resultados[np][1][2]=recorridos;
			resultados[np][1][3]=fin-ini;
			comparaciones=intercambios=recorridos=0;
		}

	}
	
	
	static class MezclaNatural {
		static long comparaciones=0;
		static long intercambios=0;
		static long recorridos=0;
		
		public static int[] mezclaDirecta (int[] vector) {
			int i,j,k;
			if(vector.length>1) {
				int nElementosIzquierda = vector.length/2;
				int nElementosDerecha = vector.length - nElementosIzquierda;
				int vectorI[] = new int [nElementosIzquierda];
				int vectorD[] = new int [nElementosDerecha];
				recorridos+=1;
				for (i = 0; i < nElementosIzquierda; i++) {
					intercambios+=1;
					vectorI[i]=vector[i];
				}
				recorridos+=1;
				for (i = nElementosIzquierda; i < nElementosIzquierda+nElementosDerecha; i++){
					intercambios+=1;
					vectorD[i-nElementosIzquierda] = vector[i];
				}
				vectorI = mezclaDirecta(vectorI);
				vectorD = mezclaDirecta(vectorD);
				i=0;
				j=0;
				k=0;
				recorridos+=1;
				while(vectorI.length!=j && vectorD.length!=k) {
					intercambios+=1;
					comparaciones+=1;
					if(vectorI[j]<vectorD[k]) {
						vector[i]=vectorI[j];
						i++;
						j++;
					}else {
						vector[i]=vectorD[k];
						i++;
						k++;
					}//Else
				}//While
				recorridos+=1;
				while(vectorI.length!=j) {
					intercambios+=1;
					vector[i] = vectorI[j];
					i++;
					j++;
				}
				recorridos+=1;
				while(vectorD.length!=k) {
					intercambios+=1;
					vector[i] = vectorD[k];
					i++;
					k++;
				}	
			}
			return vector;
		}
		//MezclaDirecta2
		public static void mezclaDirecta2 (int[] vector) {
			int i,j,k;
			if(vector.length>1) {
				int nElementosIzquierda = vector.length/2;
				int nElementosDerecha = vector.length - nElementosIzquierda;
				int vectorI[] = new int [nElementosIzquierda];
				int vectorD[] = new int [nElementosDerecha];
				recorridos+=1;
				for (i = 0; i < nElementosIzquierda; i++) {
					intercambios+=1;
					vectorI[i]=vector[i];
				}
				recorridos+=1;
				for (i = nElementosIzquierda; i < nElementosIzquierda+nElementosDerecha; i++){
					intercambios+=1;
					vectorD[i-nElementosIzquierda] = vector[i];
				}
				vectorI = mezclaDirecta(vectorI);
				vectorD = mezclaDirecta(vectorD);
				i=0;
				j=0;
				k=0;
				recorridos+=1;
				while(vectorI.length!=j && vectorD.length!=k) {
					comparaciones+=1;
					if(vectorI[j]<vectorD[k]) {
						vector[i]=vectorI[j];
						i++;
						j++;
					}else {
						vector[i]=vectorD[k];
						i++;
						k++;
					}//Else
				}//While
				recorridos+=1;
				while(vectorI.length!=j) {
					intercambios+=1;
					vector[i] = vectorI[j];
					i++;
					j++;
				}
				recorridos+=1;
				while(vectorD.length!=k) {
					intercambios+=1;
					vector[i] = vectorD[k];
					i++;
					k++;
				}	
			}
		}
		
		public static void mezclaNatural(int[] nums,int np) {
			int numeros[]= nums.clone();
			
			int izquierda =0;
			int izq =0;
			int derecha = numeros.length-1;//Sin el error de nullPointerExepcion
			int der = derecha;
			boolean ordenado = false;
			
			long ini = System.nanoTime();
			do {
				ordenado = true;
				izquierda = 0;
				while(izquierda<derecha) {
					izq =izquierda;
					recorridos+=1;
					while(izq < derecha && numeros[izq]<=numeros[izq+1]) {
						izq++;
					}
					der = izq +1;
					recorridos+=1;
					while(der==derecha-1 || der<derecha && numeros[der]<=numeros[der+1]) {
						der++;
					}
					comparaciones+=1;
					if(der<=derecha) {
						mezclaDirecta2(numeros);
						
						ordenado = false;
					}
					izquierda=izq;
					
				}
			}while(!ordenado);
			long fin = System.nanoTime();
			
			resultados[np][2][0]=comparaciones;
			resultados[np][2][1]=intercambios;
			resultados[np][2][2]=recorridos;
			resultados[np][2][3]=fin-ini;
			
			comparaciones=intercambios=recorridos=0;
			
			
		
		}
		
	}//Mezcla natural
	
	
}

public class PruebaPruebasDeEstres {

	public static void main(String[] args) {
		
		String metodos[]= {"Intercalacion","Mezcla directo","Mezcla natural"};
		for (int i = 0; i < 4; i++) {
			int nums[]=GeneracionNumeros.generarNumerosAleatorios((int) (1000*Math.pow(10, i)));
			MetodosOrdenamiento.Intercalacion.ordenacionIntercalacion(nums, i);
			MetodosOrdenamiento.MezclaDirecto.llamadaOrdenamientoMezclaDirecto(nums, i);
			MetodosOrdenamiento.MezclaNatural.mezclaNatural(nums, i);
			
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
