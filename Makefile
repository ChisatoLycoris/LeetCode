# Makefile for LeetCode Java Project

# Directories
SRC_DIR = src
OUT_DIR = out

# Java source files
PATTERN_SRC = $(shell find $(SRC_DIR)/patterns -name "*.java")
DIFFICULTY_SRC = $(shell find $(SRC_DIR)/difficulty -name "*.java")
PROBLEM_SRC = $(shell find $(SRC_DIR)/problems -name "*.java")
ALL_SRC = $(PATTERN_SRC) $(DIFFICULTY_SRC) $(PROBLEM_SRC)

# Java compiler
JAVAC = javac
JAVA = java

# Compiler flags
JAVAC_FLAGS = -d $(OUT_DIR) -sourcepath $(SRC_DIR)

# Colors for output
GREEN = \033[0;32m
YELLOW = \033[0;33m
BLUE = \033[0;34m
NC = \033[0m # No Color

.PHONY: help compile build clean run list-problems

# Default target
help:
	@printf "$(BLUE)LeetCode Java Project - Available targets:$(NC)\n"
	@printf "\n"
	@printf "  $(GREEN)make compile$(NC)     - Compile all Java source files\n"
	@printf "  $(GREEN)make build$(NC)       - Same as compile\n"
	@printf "  $(GREEN)make clean$(NC)       - Remove all compiled class files\n"
	@printf "  $(GREEN)make run PROBLEM=<number>$(NC)\n"
	@printf "                     - Run a specific problem with main() method\n"
	@printf "                       Example: make run PROBLEM=0904\n"
	@printf "                       Example: make run PROBLEM=0015\n"
	@printf "  $(GREEN)make list-problems$(NC) - List all problems with main() methods\n"
	@printf "  $(GREEN)make help$(NC)        - Show this help message\n"
	@printf "\n"

# Compile all Java files
compile: $(OUT_DIR)
	@printf "$(YELLOW)Sourcing SDKMAN and compiling all Java files...$(NC)\n"
	@bash -c 'source ~/.sdkman/bin/sdkman-init.sh 2>/dev/null || true; \
		$(JAVAC) $(JAVAC_FLAGS) $(ALL_SRC) && \
		printf "$(GREEN)✓ Compilation successful!$(NC)\n" || \
		(printf "$(RED)✗ Compilation failed!$(NC)\n" && exit 1)'

build: compile

# Create output directory if it doesn't exist
$(OUT_DIR):
	@mkdir -p $(OUT_DIR)

# Clean compiled files
clean:
	@printf "$(YELLOW)Cleaning compiled files...$(NC)\n"
	@rm -rf $(OUT_DIR)
	@printf "$(GREEN)✓ Clean complete!$(NC)\n"

# Run a specific problem
# Usage: make run PROBLEM=0015 or make run PROBLEM=_0015_3Sum
run:
ifndef PROBLEM
	@printf "$(YELLOW)Usage: make run PROBLEM=<number>$(NC)\n"
	@printf "Example: make run PROBLEM=0904\n"
	@printf "Example: make run PROBLEM=0015\n"
	@printf "Example: make run PROBLEM=0001\n"
	@printf "\n"
	@printf "Run 'make list-problems' to see all problems with main() methods\n"
else
	@printf "$(YELLOW)Running problem $(PROBLEM)...$(NC)\n"
	@bash -c 'source ~/.sdkman/bin/sdkman-init.sh 2>/dev/null || true; \
		if [[ "$(PROBLEM)" =~ ^[0-9]+$$ ]]; then \
			FILE=$$(find $(SRC_DIR)/problems -name "_$(PROBLEM)_*.java" | head -1); \
		else \
			FILE=$$(find $(SRC_DIR)/problems -name "$(PROBLEM).java" | head -1); \
		fi; \
		if [ -z "$$FILE" ]; then \
			printf "$(RED)Error: Problem $(PROBLEM) not found!$(NC)\n"; \
			exit 1; \
		fi; \
		PACKAGE=$$(echo $$FILE | sed "s|$(SRC_DIR)/||; s|/|.|g; s|.java||"); \
		CLASS=$$(basename $$FILE .java); \
		printf "$(BLUE)Running: $$CLASS ($$PACKAGE)$(NC)\n"; \
		$(JAVA) -cp $(OUT_DIR) $$PACKAGE || \
		(printf "$(RED)✗ Execution failed! Make sure the problem has a main() method.$(NC)\n" && exit 1)'
endif

# List all problems that have main() methods
list-problems:
	@printf "$(BLUE)Problems with main() methods:$(NC)\n"
	@printf "\n"
	@grep -l "public static void main" $(SRC_DIR)/problems/**/*.java | \
		sed 's|.*/||; s|\.java||' | \
		sort || printf "No problems with main() methods found\n"

# Rebuild (clean + compile)
rebuild: clean compile
	@printf "$(GREEN)✓ Rebuild complete!$(NC)\n"
