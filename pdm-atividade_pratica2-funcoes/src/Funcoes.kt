/*
//////////////////////////
Leia o escopo do main para entender o que deverá ser feito na atividade;
//////////////////////////
*/

// map contendo as disciplinas e as notas
val materiasENotas = mutableMapOf<String, MutableList<Double>>();

/**
 * Adiciona uma disciplina no dicionário mutável,
 * Recebe um array de notas (opcional)
 * Retorna true se conseguiu, false se não.
 */

/*Código original
fun adicionarDisciplina(materia: String, notas: MutableList<Double>): Boolean {
    return materiasENotas.put(materia, notas) != null
}*/

// O código original estava implementado de uma forma que o argumento "notas" era obrigatório, então fiz a pequena alteração abaixo, de modo que "notas" será inicializada como uma lista vazia, caso não seja passado algum argumento
fun adicionarDisciplina(materia: String, notas: MutableList<Double> = mutableListOf()): Boolean {
    return materiasENotas.put(materia, notas) != null
}

/**
 * Adiciona uma nota à lista de notas de uma determinada matéria;
 * Retorna true se conseguiu adicionar, false se não conseguiu.
 */
fun adicionarNota(materia: String, nota: Double): Boolean {
    val notasDaMateria = materiasENotas[materia]

    return if (notasDaMateria != null) {
        notasDaMateria.add(nota)
        true
    } else {
        false
    }
}

/**
 *Mostra na tela todas as notas presentes em uma matéria, no seguinte formato:
 * Materia: {nome da materia}
 * Nota 1: 5.4 \n
 * Nota 2: 7.8 \n
 * ...
 * Nota n: 10.0 \n
 * \n
 * Média:  {5.4 + 7.8 + ... + 10.0 / n} \n [TO DO <////////]
 * \n
 *
 * Caso não encontre a materia no map, mostre:
 * Materia {nome da materia} não encontrada \n
 *
 * Caso não seja possível mostar as notas, mostre:
 * Não foi possível mostrar as notas da matéria {nome da materia} \n
 */
fun mostrarNotas(materia:String){
    if(!materiasENotas.containsKey(materia)){
        println("Materia $materia não encontrada")
    }
    else{
        val listaNotas = materiasENotas[materia]

        if (listaNotas != null) {
            var cont = 1
            for(nota:Double in listaNotas){
                println("Nota ${cont++}: $nota")
            }
        }
        else{
            println("Não foi possível mostrar as notas da matéria ${materia}")
        }

        println()
    }
}

/*Retorna a média obtida apartir de uma lista de notas */
fun calcularMedia(){
    // A ser implementado
}

/**
 *Adiciona várias notas de uma só vez em uma matéria
 * retorne true se conseguiu adicionar, false se não consegiu.
 * */
fun adicionarVariasNotas(materia:String, vararg nota:Double){
    // A ser implementado
}

fun main(){
    val materia1 = "Algoritmo e Programação Estruturada";
    // 1. adicionarDisciplinas -> adicione 1 disciplina ao map materiasENotas, através de atribuição possicional
    adicionarDisciplina(materia1);

    // 2. adicionarDisciplinas -> adicione 1 disciplina ao map materiasENotas, através de atribuição nomeada
    val materia2 = "Banco de Dados I";
    adicionarDisciplina(materia=materia2);

    // 3. adicionarDisciplinas -> altere a função adicionarDisciplinas para que o parametro notas possua um valor padrão. Dica: utilize mutableListOf()
    // Override de adicionarDisciplina()
    fun adicionarDisciplina(materia: String, notas: MutableList<Double> = mutableListOf(7.0)): Boolean {
        return materiasENotas.put(materia, notas) != null;
    }

    // 4. adicionarDisciplinas -> adicione 1 disciplina ao map materiasENotas, sem atribuir valores a notas
    val materia3 = "Programação Orientada a Objetos";
    adicionarDisciplina(materia3); // Adiciona nota 7.0 a esta matéria

    // 5. adicionarNota -> adicione 3 notas para as 3 disciplinas
    adicionarNota(materia1, 10.0);
    adicionarNota(materia1, 6.0);
    adicionarNota(materia1, 8.0);

    adicionarNota(materia2, 6.8);
    adicionarNota(materia2, 9.5);
    adicionarNota(materia2, 8.0);

    adicionarNota(materia3, 7.4);
    adicionarNota(materia3, 8.8);
    adicionarNota(materia3, 7.9);

    // 6. mostrarNotas -> Mostre as notas das 3 disciplinas
    /*for (nota in materiasENotas){
        println(nota);
    }*/

    println("Exibindo notas - $materia1"); // APS | 10, 6 e 8
    mostrarNotas(materia1);

    println("Exibindo notas - $materia2"); // BD1 | 6.8, 9.5 e 8
    mostrarNotas(materia2);

    println("Exibindo notas - $materia3"); // POO | 7, 7.4, 8.8 e 7.9
    mostrarNotas(materia3);

    // 7. adicionarDisciplina -> adicione mais 1 disciplina
    val materia4 = "Persistência de Objetos";
    adicionarDisciplina(materia4);

    // 8. adicionarVariasNotas -> implemente o metodo adicionarVariasNotas();
    fun adicionarVariasNotas(materia: String, vararg notas: Double): Boolean {

        val listaNotasMateria = materiasENotas[materia];
        if (listaNotasMateria != null) { // Verifica se a matéria existe no map
            listaNotasMateria.addAll(notas.toList()); // Caso exista, adiciona as notas à disciplina em questão
            return true;
        }
        else { // Caso não exista, retorna false
            return false;
        }
    }

    // 9. adicionarVariasNotas -> adicione 3 notas para a disciplina que você acabou de criar
    adicionarVariasNotas(materia4, 7.5, 8.5, 8.0);

    // 10. mostrarNotas -> mostre as notas da disciplina que você acabou de criar;
    println("Exibindo notas - $materia4");
    mostrarNotas(materia4); // POB | 7, 7.5, 8.5 e 8

    // Bônus: (Não vai ganhar nada, mas vai ganhar mais conhecimento >:O)

    // 11: calcularMedia -> Implemente a função calcularMedia()
    fun calcularMedia(materia: String): Double {
        val materiaAtual = materiasENotas[materia];

        if (materiaAtual != null) { // Verifica se a matéria em questão existe no map
            var soma = 0.0;

            for (nota in materiaAtual) {
                soma += nota;
            }

            return soma / materiaAtual.size;
        }
        else { // Caso não exista
            return 0.0;
        }
    }

    // 12: calcularMedia -> calcule a media de 2 disciplinas
    println("Média de $materia1: ${calcularMedia(materia1)}"); // 8
    println("Média de $materia2: ${calcularMedia(materia2)}"); // 8.1

    // 13: mostrarNotas -> altere o mostrarNotas() para que ele mostre também a media das disciplinas
    // Override de mostrarNotas()
    fun mostrarNotas(materia:String) {
        if(!materiasENotas.containsKey(materia)){
            println("Materia $materia não encontrada");
        }
        else{
            val listaNotas = materiasENotas[materia];

            if (listaNotas != null) {
                var cont = 1;

                for(nota:Double in listaNotas){
                    println("Nota ${cont++}: $nota");
                }

                val media = calcularMedia(materia);
                println("Média de $materia: $media");
            }
            else{
                println("Não foi possível mostrar as notas da matéria ${materia}")
            }

            println()
        }
    }

    // 14: mostrarNotas -> mostre as notas de 1 disciplina
    println("Exibindo notas e média - $materia4");
    mostrarNotas(materia4); // POB | Notas: 7,7.5, 8.5 e 8 | Média: 7.75
}