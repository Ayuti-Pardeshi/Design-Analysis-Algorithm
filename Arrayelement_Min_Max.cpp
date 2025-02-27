#include <iostream>
#include <limits.h> //INT_MAX and INT_MIN are defined in <limits.h>
using namespace std;

int setmini (int arr[], int n)
{
	int  minimum = INT_MAX; // INT_MAX 2147483647
	for (int i=0; i<n; i++)
	{
		if(arr[i]<minimum)
			minimum = arr[i];
	}

	return minimum;
}

int setmax (int arr[], int n)
{
	int maximum = INT_MIN; // -2147483648
	for(int i=0; i<n; i++)
	{
		if(arr[i]>maximum)
			maximum = arr[i];
	}

	return maximum;
}
int main()
{
	int n; // number of elements to be accepted
	cout<<"Enter the number of elements: ";
	cin>>n;
	int arr[n];

	for (int i=0 ; i<n; i++)
	{
		cin>>arr[i];
	}

	cout << "Minimum element is: " << setmini(arr, n)<<endl;
	cout << "Maximum element is: " << setmax(arr, n);


	return 0;
}
