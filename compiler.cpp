#include <bits/stdc++.h>
using namespace std;

enum TokenType {
    TOK_INT, TOK_FLOAT, TOK_CHAR, TOK_VOID, TOK_RETURN,
    TOK_IF, TOK_ELSE, TOK_WHILE, TOK_FOR,
    TOK_ID, TOK_NUM, TOK_FNUM, TOK_STRING,
    TOK_ASSIGN, TOK_PLUS, TOK_MINUS, TOK_STAR, TOK_SLASH,
    TOK_EQ, TOK_NEQ, TOK_LT, TOK_GT, TOK_LE, TOK_GE,
    TOK_LPAREN, TOK_RPAREN, TOK_LBRACE, TOK_RBRACE,
    TOK_LBRACKET, TOK_RBRACKET, TOK_SEMICOLON, TOK_COMMA,
    TOK_HASH, TOK_INCLUDE, TOK_EOF, TOK_UNKNOWN
};

struct Token {
    TokenType type; string value; int line;
    Token(TokenType t, const string& v, int ln) : type(t), value(v), line(ln) {}
};

struct TAC {
    string result, arg1, op, arg2;
};

// ==================== COMMENT REMOVAL ====================
string removeComments(string src) {
    string out; out.reserve(src.size());
    for (size_t i = 0; i < src.size(); ++i) {
        if (i + 1 < src.size() && src[i] == '/' && src[i+1] == '/') {
            while (i < src.size() && src[i] != '\n') ++i;
        } else if (i + 1 < src.size() && src[i] == '/' && src[i+1] == '*') {
            i += 2;
            while (i + 1 < src.size() && !(src[i] == '*' && src[i+1] == '/')) ++i;
            i += 2;
        } else {
            out += src[i];
        }
    }
    return out;
}

// ==================== TOKENIZER ====================
const map<string, TokenType> keywords = {
    {"int", TOK_INT}, {"float", TOK_FLOAT}, {"char", TOK_CHAR},
    {"void", TOK_VOID}, {"return", TOK_RETURN}, {"if", TOK_IF},
    {"else", TOK_ELSE}, {"while", TOK_WHILE}, {"for", TOK_FOR},
    {"include", TOK_INCLUDE}
};

vector<Token> tokenize(const string& code) {
    vector<Token> tokens;
    size_t i = 0, n = code.size();
    int line = 1;

    while (i < n) {
        if (code[i] == '\n') { ++line; ++i; continue; }
        if (isspace((unsigned char)code[i])) { ++i; continue; }

        if (isdigit((unsigned char)code[i])) {
            string num; bool isFloat = false;
            while (i < n && (isdigit((unsigned char)code[i]) || code[i] == '.')) {
                if (code[i] == '.') isFloat = true;
                num += code[i++];
            }
            tokens.emplace_back(isFloat ? TOK_FNUM : TOK_NUM, num, line);
            continue;
        }

        if (isalpha((unsigned char)code[i]) || code[i] == '_') {
            string word;
            while (i < n && (isalnum((unsigned char)code[i]) || code[i] == '_' || code[i] == '.'))
                word += code[i++];
            auto it = keywords.find(word);
            tokens.emplace_back(it != keywords.end() ? it->second : TOK_ID, word, line);
            continue;
        }

        if (code[i] == '"') {
            string str; ++i;
            while (i < n && code[i] != '"') str += code[i++];
            if (i < n) ++i;
            tokens.emplace_back(TOK_STRING, str, line);
            continue;
        }

        if (i + 1 < n) {
            string p = {code[i], code[i+1]};
            if (p == "==") { tokens.emplace_back(TOK_EQ, p, line); i += 2; continue; }
            if (p == "!=") { tokens.emplace_back(TOK_NEQ, p, line); i += 2; continue; }
            if (p == "<=") { tokens.emplace_back(TOK_LE, p, line); i += 2; continue; }
            if (p == ">=") { tokens.emplace_back(TOK_GE, p, line); i += 2; continue; }
        }

        TokenType tt = TOK_UNKNOWN;
        char c = code[i];
        if (c == '=') tt = TOK_ASSIGN;
        else if (c == '+') tt = TOK_PLUS;
        else if (c == '-') tt = TOK_MINUS;
        else if (c == '*') tt = TOK_STAR;
        else if (c == '/') tt = TOK_SLASH;
        else if (c == '<') tt = TOK_LT;
        else if (c == '>') tt = TOK_GT;
        else if (c == '(') tt = TOK_LPAREN;
        else if (c == ')') tt = TOK_RPAREN;
        else if (c == '{') tt = TOK_LBRACE;
        else if (c == '}') tt = TOK_RBRACE;
        else if (c == '[') tt = TOK_LBRACKET;
        else if (c == ']') tt = TOK_RBRACKET;
        else if (c == ';') tt = TOK_SEMICOLON;
        else if (c == ',') tt = TOK_COMMA;
        else if (c == '#') tt = TOK_HASH;

        tokens.emplace_back(tt, string(1, c), line);
        ++i;
    }
    tokens.emplace_back(TOK_EOF, "EOF", line);
    return tokens;
}

// ==================== PARSER ====================
class Parser {
    const vector<Token>& tokens;
    size_t pos = 0;
    int tempCount = 0;
    vector<TAC>* tac;

    const Token& peek(int off = 0) const {
        size_t idx = pos + off;
        return idx < tokens.size() ? tokens[idx] : tokens.back();
    }
    const Token& consume() { return tokens[pos++]; }
    bool check(TokenType t) const { return peek().type == t; }

    void expect(TokenType t, const string& msg) {
        if (check(t)) { consume(); return; }
        throw runtime_error("Syntax Error line " + to_string(peek().line) + ": " + msg);
    }

    string newTemp() { return "t" + to_string(tempCount++); }

    void emit(const string& res, const string& a1, const string& op = "", const string& a2 = "") {
        tac->push_back({res, a1, op, a2});
    }

public:
    Parser(const vector<Token>& tks, vector<TAC>& t) : tokens(tks), tac(&t) {}

    void parse() {
        while (check(TOK_HASH)) {
            consume();
            while (!check(TOK_EOF) && peek().type != TOK_INT && peek().type != TOK_FLOAT && peek().type != TOK_VOID) consume();
        }

        if (peek().type == TOK_INT || peek().type == TOK_FLOAT || peek().type == TOK_VOID) consume();
        if (check(TOK_ID)) consume(); // function name

        if (check(TOK_LPAREN)) {
            consume();
            while (!check(TOK_RPAREN) && !check(TOK_EOF)) consume();
            expect(TOK_RPAREN, "expected ')'");
        }
        parseBlock();
    }

private:
    void parseBlock() {
        expect(TOK_LBRACE, "expected '{'");
        while (!check(TOK_RBRACE) && !check(TOK_EOF))
            parseStmt();
        expect(TOK_RBRACE, "expected '}'");
    }

    void parseStmt() {
        if (peek().type == TOK_INT || peek().type == TOK_FLOAT || peek().type == TOK_CHAR) {
            consume();
            parseDecl();
            return;
        }
        if (check(TOK_RETURN)) {
            consume();
            if (!check(TOK_SEMICOLON)) {
                string rhs = parseExpr();
                emit("return", rhs);
            } else {
                emit("return", "0");  // default return 0
            }
            expect(TOK_SEMICOLON, "expected ';'");
            return;
        }
        if (check(TOK_ID)) {
            string name = consume().value;
            if (check(TOK_ASSIGN)) {
                consume();
                string rhs = parseExpr();
                emit(name, rhs, "=");
                expect(TOK_SEMICOLON, "expected ';'");
                return;
            }
            // function call (skip for now)
            if (check(TOK_LPAREN)) {
                consume(); int d = 1;
                while (d > 0 && !check(TOK_EOF)) {
                    if (check(TOK_LPAREN)) ++d;
                    else if (check(TOK_RPAREN)) --d;
                    consume();
                }
                expect(TOK_SEMICOLON, "expected ';'");
            }
            return;
        }
        if (check(TOK_SEMICOLON)) { consume(); return; }
    }

    void parseDecl() {
        do {
            string var = consume().value; // identifier
            if (check(TOK_ASSIGN)) {
                consume();
                string rhs = parseExpr();
                emit(var, rhs, "=");
            }
        } while (check(TOK_COMMA) && (consume(), true));
        expect(TOK_SEMICOLON, "expected ';'");
    }

    string parseExpr() {
        string left = parseTerm();
        while (check(TOK_PLUS) || check(TOK_MINUS)) {
            string op = consume().value;
            string right = parseTerm();
            string tmp = newTemp();
            emit(tmp, left, op, right);
            left = tmp;
        }
        return left;
    }

    string parseTerm() {
        string left = parseFactor();
        while (check(TOK_STAR) || check(TOK_SLASH)) {
            string op = consume().value;
            string right = parseFactor();
            string tmp = newTemp();
            emit(tmp, left, op, right);
            left = tmp;
        }
        return left;
    }

    string parseFactor() {
        if (check(TOK_LPAREN)) {
            consume();
            string val = parseExpr();
            expect(TOK_RPAREN, "expected ')'");
            return val;
        }
        if (check(TOK_ID) || check(TOK_NUM) || check(TOK_FNUM)) {
            return consume().value;
        }
        throw runtime_error("Invalid expression at line " + to_string(peek().line));
    }

    void parseBlockOrStmt() {
        if (check(TOK_LBRACE)) parseBlock();
        else parseStmt();
    }
};

// ==================== TAC TO ASSEMBLY ====================
string tacToAssembly(const vector<TAC>& tac) {
    ostringstream oss;
    oss << "; Generated Assembly (Mini C Compiler)\n\n"
        << "section .text\n"
        << "global main\n"
        << "main:\n";

    int reg = 0;
    for (const auto& t : tac) {
        if (t.op == "=" && t.arg2.empty()) {
            // Load immediate or variable
            if (isdigit(t.arg1[0]) || (t.arg1[0] == '-' && isdigit(t.arg1[1]))) {
                oss << "    MOV " << t.arg1 << ", " << t.result << "\n";
            } else {
                oss << "    MOV " << t.arg1 << ", " << t.result << "\n";
            }
        }
        else if (!t.op.empty() && !t.arg2.empty()) {
            string r = "R" + to_string(reg++);
            oss << "    MOV " << t.arg1 << ", " << r << "\n";
            if (t.op == "+") oss << "    ADD " << t.arg2 << ", " << r << "\n";
            else if (t.op == "-") oss << "    SUB " << t.arg2 << ", " << r << "\n";
            else if (t.op == "*") oss << "    MUL " << t.arg2 << ", " << r << "\n";
            else if (t.op == "/") oss << "    DIV " << t.arg2 << ", " << r << "\n";
            oss << "    MOV " << r << ", " << t.result << "\n";
        }
        else if (t.result == "return") {
            oss << "    MOV " << t.arg1 << ", R0\n";
            oss << "    RET\n";
        }
    }
    oss << "    RET\n";
    return oss.str();
}

// ==================== MAIN ====================
int main() {
    ifstream fin("input.txt");
    if (!fin) {
        cerr << "Cannot open input.txt\n";
        return 1;
    }
    string code((istreambuf_iterator<char>(fin)), istreambuf_iterator<char>());

    cout << "=== Mini C Compiler ===\n";
    string clean = removeComments(code);
    auto tokens = tokenize(clean);

    cout << "Tokens: " << tokens.size() - 1 << "\n";

    vector<TAC> tac;
    try {
        Parser parser(tokens, tac);
        parser.parse();

        cout << "Parsing successful. TAC: " << tac.size() << " instructions\n";

        ofstream fout("output.txt");
        fout << "; === Three-Address Code ===\n";
        for (const auto& t : tac) {
            if (t.op == "=" && t.arg2.empty())
                fout << t.result << " = " << t.arg1 << "\n";
            else if (!t.op.empty())
                fout << t.result << " = " << t.arg1 << " " << t.op << " " << t.arg2 << "\n";
            else if (t.result == "return")
                fout << "return " << t.arg1 << "\n";
        }
        fout << "\n" << tacToAssembly(tac);
        fout.close();

        cout << "Output written to output.txt\n";
    } catch (const exception& e) {
        cout << "Error: " << e.what() << endl;
        ofstream fout("output.txt");
        fout << "ERROR: " << e.what();
    }
    return 0;
}
