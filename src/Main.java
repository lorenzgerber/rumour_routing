//Vet inte om det var meningen att det h�r skulle vara en egen klass
//men n�gonstans ska vi ju ha det!
//
//Det som m�ste l�ggas till h�r �r undantag.

public class Main
{
	public static void main(String[] args)
	{
		int nodesX			= 50;
		int nodesY 		= 50;
		int noNodes		= nodesX * nodesY; //On�dig?

		int time 			= 10000;

		int probEvent 		= 0,0001;

		int probAgent 		= 0,5;
		int ttlAgent 		= 50;

		int queryPeriodicity 	= 400;
		int queryNodes	 	= 4;
		int ttlQuery 		= 45;

		Environment environment = new Environment(nodesX, nodesY, time, probAgent, probEvent, ttlAgent, queryPeriodicity, queryNodes, ttlQuery);

		environment.run();

		System.out.printf("The End");
	}
}
