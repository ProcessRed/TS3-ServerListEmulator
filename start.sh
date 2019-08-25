RED='\033[0;31m'
GREEN='\e[32m'
YELLOW='\033[1;33m'
BLUE='\033[1;34m'
PINK='\033[1;35m'
NC='\033[0m'

printf "> ${BLUE}Weblist Emulator ${NC}\n"
printf "> ${PINK}This script it was created by SonsofScripts${NC}\n"
printf "> ${PINK}Credits by Isa${NC}\n"
printf "> ${YELLOW}Visit https://github.com/ProcessRed/TS3-ServerListEmulator${NC}\n"

count=1;
Startport=200;
slots=512;
Startrndusr=100;
Endrndusr=230;

randomnames=(
	"Sons of Scripts."
  "TS3Public.de | Free Channels"
	"Ventrux.de Multigaming und Mehr"
	"➽ Synchrom TeamSpeak | »Synchrom.eu"
	"Flosing.de ]|[ Community Teamspeak³"
	"ts3for.world TeamSpeak"
	"Pentu.eu | 24/7 | DDoS Protected"
	"The Beast TeamSpeak | Public | Free & Own Channel"
	"Let's Kautsch e.V."
	"OpossumTS.net & StreamAcademy.tv | Kostenlose Teamspeak-Channel"
	"『MASTER-SKILLER 』 ❤ عيد مبارك سعيد وكل عام وانتم بخير ❤"
	"·٠•● SGC-Community Teamspeak ●•٠·"
	"[EU | GER] EINHORNSERVER © »» Gaming | Music | Talking ««"
	"OTUTS.eu - Open To Use TeamSpeak & Technical Service Provider"
	"*=IMA=* International Mobilized Army Gaming Community"
	"Visit ProcessRed on Github"
	"▶MOZZARELLE◀ [ MULTIGAMING - ITA ]"
	)
if apt -qq list screen > /dev/null 2>&1 ; then
	if [[ $1 == 'run' ]]; then
		printf "> ${RED}Starting the weblistemulator..${NC}\n"
		for ((i=0; i < count; i++)) ; do
		  selectedexpression=${randomnames[$RANDOM % ${#randomnames[@]} ]}
		  screen -dmS webreport-$((i)) java -jar Weblistemulator.jar "$selectedexpression" $(( Startport + i )) $(( slots )) $(( Startrndusr )) $((Endrnduser))
		done
		printf "> ${YELLOW}$count fake entries were sent successfully!${NC}\n"
	elif [[ $1 == 'stop' ]] ; then
		for session in $(screen -ls | grep -o '[0-9]*\.webreport-[0-9]*') ; do
			screen -S "${session}" -X quit
		done
		printf "> ${YELLOW}Der Weblist Emualtor was succesfull stoppe!${NC}\n"
	else
		printf "> ${RED}Type run to flood the TS3 Server List! As example ./runSLE-flood.sh run/stop${NC}\n"
	fi
else
	echo -e "> Screen not found! Install? (y/n) \c"
	read
	if "$REPLY" = "y" ; then
		sudo apt-get install screen
	else
		printf "> ${RED}Screen installer aborted!${NC}\n"
	fi
fi
