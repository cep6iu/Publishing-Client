The App primary function is to provide an interface between the internal Mobile Operator networks and 3rd party network based Mobile data providers (known internally as Partners).
Examples of Partners are:
•	Providers of specialized devices like Blackberry or Sidekick, etc.
•	Application service providers like Blogger, SNAPin, Wireless Village, Infospace, Medio, QPass
Mobile Operator is responsible for selling the services provided by these Partners, so it is necessary for the Partners to be updated in
REAL-TIME with the latest set of services the Subscriber has purchased and is entitled to.
Mobile Operator uses a service provisioning system. Publisher uses service and service levels to publish it to the corresponding Partners.
The Subscriber data is stored in the OpenLDAP DB.
Publisher Client shall have designed a Queue Application around an OpenLDAP Queue to play the role of the “buffer” between Publisher and Partners.
