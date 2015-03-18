#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=MinGW-Windows
CND_DLIB_EXT=dll
CND_CONF=Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/DrawUtils.o \
	${OBJECTDIR}/GameWindow.o \
	${OBJECTDIR}/include/DrawUtils.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=-L/C/Users/SWAGpad/Documents/GitHub/School/Spring2015/CS134/GameEngine/lib

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe: SDL2.lib

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe: opengl32.lib

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe: glu32.lib

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe: glew32.lib

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.c} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/DrawUtils.o: DrawUtils.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -I/C/Users/SWAGpad/Documents/GitHub/School/Spring2015/CS134/GameEngine/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/DrawUtils.o DrawUtils.c

${OBJECTDIR}/GameWindow.o: GameWindow.c 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.c) -I/C/Users/SWAGpad/Documents/GitHub/School/Spring2015/CS134/GameEngine/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/GameWindow.o GameWindow.c

${OBJECTDIR}/include/DrawUtils.o: include/DrawUtils.c 
	${MKDIR} -p ${OBJECTDIR}/include
	${RM} "$@.d"
	$(COMPILE.c) -I/C/Users/SWAGpad/Documents/GitHub/School/Spring2015/CS134/GameEngine/include -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/include/DrawUtils.o include/DrawUtils.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/gameengine.exe

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
