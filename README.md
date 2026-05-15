Android App Development using GenAI- Jan-Aushadhi Finder 
(Healthcare)
1. The Problem Statement:
Generic medicines at Jan-Aushadhi stores are cheaper than branded ones. However, many people don't 
know where the nearest store is or if the specific medicine they need is available in a "Generic Version."
2. Detailed Description (The Vision)
Jan-Aushadhi Finder is a "Healthcare Savings Tool." It serves two purposes: finding the nearest generic 
medicine outlet and acting as a "Brand-to-Generic" translator. Users can enter a branded medicine name 
and see the equivalent salt/generic name along with the potential price difference.
3. App Usage & User Flow
● Medicine Search: Enter branded name -> See Generic equivalent and Price.
● Store Locator: Map showing Jan-Aushadhi Kendras within 10kms.
● Stock Request: (Simulated) Ask a store if they have a medicine in stock.
● Reminders: A tool to track monthly medicine refills.
4. Technical Implementation & Hints
● Search: Implement a Filterable RecyclerView for a database of 500+ medicine names.
● Maps: Use Google Maps API with "Open Now" status (Simulated).
● Logic: Calculate "Total Savings" if the userswitchesto genericsfor their prescription.
5. Impact Goals
● Affordable Healthcare: Reducing out-of-pocket medical expensesfor the poor.
● Health Literacy: Educating citizens that "Generic" does not mean "Low Quality."
● Universal Access: Ensuring government-subsidized medicines reach the last mile.
6. Success Criteria for Students
● The search must be "Fuzzy" (handle smallspelling mistakes in medicine names).
● The price comparison must be visually clear (e.g., Branded: ₹100 vs Generic: ₹20).
● The UI must be clean, clinical, and professional.
