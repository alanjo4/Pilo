#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

// TODO: Please Add headers definitions to improve code organization

namespace Pilo {
    bool hadError = false;

    void runFile(const std::string& path);
    void runPrompt();
    void error(int line, const std::string& message);
    void report(int line, const std::string& where, const std::string& message);
    void run(const std::string& source);

    void runFile(const std::string& path) {
        std::ifstream file(path);
        std::stringstream buffer;
        buffer << file.rdbuf();
        run(buffer.str());
        if (hadError) {
            exit(65);
        }
    }

    void runPrompt() {
        std::string line;
        for (;;) {
            std::cout << "> ";
            if (!std::getline(std::cin, line)) {
                break;
            }
            run(line);
            hadError = false;
        }
    }

    void error(int line, const std::string& message) {
        report(line, "", message);
    }

    void report(int line, const std::string& where, const std::string& message) {
        std::cerr << "[line " << line << "] Error" << where << ": " << message << std::endl;
        hadError = true;
    }

    void run(const std::string& source) {
        // TODO: Implement the Pilo interpreter logic
    }
}


int main(int argc, char** argv) {
    if (argc > 2) {
        std::cout << "Pilo language: " << std::endl;
        return 64;
    } else if (argc == 2) {
        Pilo::runFile(argv[1]);
    } else {
        Pilo::runPrompt();
    }

    return 0;
}
