// Construtor da classe Livro
class Livro(val titulo: String, val preco: Float) {
    // Override do toString()
    override fun toString(): String {
        return "Livro: Titulo = $titulo, Preco = $preco"
    }
}

// Função que não recebe argumentos e vai exibir um menu no prompt
fun menu() {
    println("1 - Cadastrar livro")
    println("2 - Excluir livro")
    println("3 - Buscar livro")
    println("4 - Editar livro")
    println("5 - Listar livros")
    println("6 - Listar livros que começam com letra escolhida")
    println("7 - Listar livros com preço abaixo do informado")
    println("8 - Sair")
}

// Função que não recebe argumentos e retorna uma string
fun inputTitulo(): String {
    print("Digite o titulo do livro: ")

    return readlnOrNull() ?:"" // Caso o usuário não digite nada, readlnOrNull() retorna null.
}

// Função que não recebe argumentos e retorna um float
fun inputPreco(): Float {
    print("Digite o preco do livro: ")
    val preco = readlnOrNull()!!.toFloat()

    return preco
}

// Recebe uma lista de objetos do tipo Livro como argumento
fun cadastrarLivro(repositorio: MutableList<Livro>) {
    val titulo = inputTitulo()
    val preco = inputPreco()

    repositorio.add(Livro(titulo, preco)) // Instancia um objeto do tipo Livro com os valores lidos
    println("\nCadastrado com sucesso!\n")
}

// Remove um livro do repositorio
fun excluirLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    repositorio.remove(livro)
    println("Livro removido com sucesso!")
}

// Busca por um livro a partir de um título e retorna este livro
fun buscarNome(repositorio: MutableList<Livro>): Livro? {
    val titulo = inputTitulo()
    return repositorio.find { it.titulo == titulo }
}

// Atualiza as informações de um livro
fun editarLivro(repositorio: MutableList<Livro>) {
    // Como foi definido que os atributos de Livro são imutáveis, iremos seguir a lógica de remover o livro que queremos editar e criar um novo livro contendo as informações atualizadas
    val livro = buscarNome(repositorio); // Buscamos pelo livro
    if (livro != null) { // Se o livro existir
        repositorio.remove(livro); // Remove o livro existente

        val novoTitulo = inputTitulo(); // Solicita o título atualizado
        val novoPreco = inputPreco(); // Solicita o preço atualizado

        val novoLivro = Livro(novoTitulo, novoPreco); // Instancia o livro com as informações atualizadas
        repositorio.add(novoLivro); // Registra o novo livro no repositório
        println("\nLivro atualizado com sucesso!");
    }
    else { // Caso o livro em questão não seja encontrado
        println("\nLivro não encontrado.");
    }
}

// Lista todos os livros cadastrados no repositório
fun listar(repositorio: MutableList<Livro>) {
    println("\nListagem de livros:\n--------------------");
    for (livro in repositorio) {
        println("Livro: $livro");
    }
    println();
}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
    print("Informe a letra: ")
    var letra = readlnOrNull() ?:""

    while(letra.length > 1) {
        print("Informe apenas uma letra: ")
        letra = readlnOrNull() ?:""
    }

    if(letra != "") {
        val livros = repositorio.filter { it.titulo.startsWith(letra) }
        livros.forEach {println(it)}
    } else {
        println("É necessário informar pelo menos um caracter para esta função executar!")
    }
}

// Função para listar livros abaixo de um determinado valor
fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
    val preco = inputPreco()
    val livros = repositorio.filter { it.preco < preco }
    livros.forEach { println(it) }
}

fun main() {
    // Cria uma lista MUTÁVEL de livros
    val repositorioLivros = mutableListOf<Livro>()
    // Populando a lista de livros
    repositorioLivros.add(Livro("Livro dos Livros", 999999.99f))
    repositorioLivros.add(Livro("Turma da Monica", 4.99f))
    repositorioLivros.add(Livro("Kotlin for Dummies", 29.99f))
    repositorioLivros.add(Livro("A", 59.99f))

    var opcao = 0
    while (opcao != 8) {
        menu()
        println(repositorioLivros[0])
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?:8

        when (opcao) {
            1 -> cadastrarLivro(repositorioLivros)
            2 -> excluirLivro(repositorioLivros)
            3 -> {
                val livro = buscarNome(repositorioLivros)
                println(livro)
            }
            4 -> editarLivro(repositorioLivros)
            5 -> listar(repositorioLivros)
            6 -> listarComLetraInicial(repositorioLivros)
            7 -> listarComPrecoAbaixo(repositorioLivros)
            8 -> println("Até a próxima :)")
        }
        Thread.sleep(3000)
    }
}