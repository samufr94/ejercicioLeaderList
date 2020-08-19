import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {

	public static void main(String[] args) {
		int m = 3;
		int longitudArray_N = 5;
		int k = 3;
		int[] a = new int[longitudArray_N];

		solution(k, m, a);

	}

	public static int[] solution(int k, int m, int[] a) {

		List<Integer> leaderList = new ArrayList<Integer>();

		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * m + 1);
		}

		System.out.println("ARRAY");
		mostrarArray(a);

		int[] copiaArray = new int[a.length];
		for (int i = 0; i < a.length - k + 1; i++) {
			System.arraycopy(a, 0, copiaArray, 0, a.length);
			copiaArray = incrementSegment(copiaArray, i, i + k);

			Arrays.sort(copiaArray);

			Integer leader = calcularLider(copiaArray);
			if (leader != 0 && !leaderList.contains(leader)) {
				leaderList.add(leader);
			}

			Collections.sort(leaderList);
		}

		System.out.println("LEADER LIST: " + leaderList);
		int[] array = leaderList.stream().mapToInt(i -> i).toArray();
		return array;

	}

	public static void mostrarArray(int array[]) {
		System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList()));
	}

	public static int[] incrementSegment(int array[], int desde, int hasta) {

		for (int i = desde; i < hasta; i++) {
			array[i]++;
		}
		return array;

	}

	public static Integer calcularLider(int array[]) {
		List<Integer> listado = Arrays.stream(array).boxed().collect(Collectors.toList());
		Integer numeroLeader = 0;
		Integer frecuenciaLeader = 0;
		for (Integer numero : listado) {
			if (numero > numeroLeader) {
				int frecuencia = Collections.frequency(listado, numero);
				if (frecuencia > frecuenciaLeader) {
					numeroLeader = numero;
					frecuenciaLeader = frecuencia;
				} else if (frecuencia == frecuenciaLeader) {
					numeroLeader = 0;
				} else {
					// NO PASA NADA
				}
			}
		}
		return numeroLeader;
	}

}
