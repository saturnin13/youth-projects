#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <math.h>

#include "arm_system.h"
#include "compiler.h"
#include "single_datapath_emulator.h"
#include "single_datapath_assembler.h"
#include "binary_file_writer.h"

int main()
{
    /*struct ARMsystem system;
    system.registers[2] = 5;
    system.registers[3] = 0x07060501;
    system.memory[8] = 4;
    system.memory[7] = 0;
    system.memory[6] = 0;
    system.memory[5] = 0;
    system.registers[16] = 2;
    uint32_t value1 = 1 << 26;
    uint32_t I = 0 << 25; // 1 offset is shifted register, 0 it is unsigned integer
    uint32_t P = 0 << 24; // 1 pre indexing, 0 post indexing
    uint32_t U = 1 << 23; // 1 offset added to Rn, 0 substracted
    uint32_t L = 1 << 20; // 1 loaded from mem, 0 stored into mem
    uint32_t Rn = 0 << 19 | 0 << 18 | 1 << 17 | 0 << 16; // base register
    uint32_t Rd = 0 << 15 | 0 << 14 | 1 << 13 | 1 << 12; // destination register
    uint32_t RsConstant = 0<< 11 | 0 << 10 | 0 << 9 | 0 << 8 | 0 << 7; // Rs or constant
    uint32_t shiftType = 0 << 6 | 0 << 5; // shit type
    uint32_t shiftValueUsed = 0 << 4; // 1 Rs, 0 Constant
    uint32_t Rm = 0 << 3 | 0 << 2 | 0 << 1 | 0 << 0; // Rm
    uint32_t offSet = RsConstant | shiftType | shiftValueUsed | Rm;
    uint32_t total = value1 | I | P | U | L | Rn | Rd | offSet;
    singledatapathEmulator(total, &system);
    printBits(total);
    printf("%i\n", system.registers[2]);
    printf("%i\n", system.registers[3]);
    printf("%i\n", system.registers[4]);
    printf("%i\n", system.memory[8]);
    printf("%i\n", system.memory[9]);
    printf("%i\n", system.memory[10]);
    printf("%i\n", system.memory[11]);*/

    printf("\n");



    Compiler *system = malloc(sizeof(Compiler) + 20000 * sizeof(uint32_t));
    printBits(singledatapathAssembler("ldr r2,  =0x4000000", system));printf("ok\n");
    singledatapathAssembler("ldr r2,=0x100", system);
    singledatapathAssembler("ldr r2, =0x0f", system);
    printf("%i\n", system->constantToAdd[0]);
    printf("%i\n", system->constantToAdd[1]);
    printf("%i\n", system->constantToAdd[2]);
    printf("%i\n", system->constantToAdd[3]);



    printf("\n");

    /*uint32_t test = 4;

    //appendFile(0x01010101, "test2.bin");
    loadFile("test2.bin", &system);

    for(int i = 0; i < 24; i++) {
        printf("%i\n", system.memory[i]);
    }*/

    return 0;
}

void printBits(uint32_t x) {
    int i;
    uint32_t mask = 1 << 31;
    for(i=0; i<32; ++i) {
        int bit = 31 - i;
        printf("%i : ", bit);
        if((x & mask) == 0){
            printf("0");
        }
        else {
            printf("1");
        }
        printf("\n");
    x = x << 1;
    }
    printf("\n");
}
