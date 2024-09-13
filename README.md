# **Step Tracker with AI Chat Terminal Interface**

## **Overview**

The **Step Tracker with AI Chat Terminal Interface** is a terminal-based tool that allows users to seamlessly interact with Hugging Face's AI models. Through a simple and efficient chat interface, users can engage in real-time conversations with state-of-the-art AI directly from the command line.

## **Features**

- **AI-Powered Conversations**: Direct interaction with Hugging Face's advanced AI models.
- **Simple Terminal Interface**: A user-friendly interface designed for smooth, uninterrupted chats.
- **Robust Error Handling**: Built-in error management to ensure seamless user experience, even in case of interruptions.

## **Requirements**

Ensure you have the following prerequisites installed on your system:

- [`curl`](https://curl.se/download.html) - for HTTP requests.
- [`jq`](https://stedolan.github.io/jq/download/) - for parsing JSON data.
- **Maven**: Ensure that [Apache Maven](https://maven.apache.org/download.cgi) is installed for running the project through Maven.

## **How to Run**

You can run the project in two ways: by executing the shell script directly or by using Maven.

### **Option 1: Run via Shell Script**

#### **Step 1: Verify Your Current Directory**

Open your terminal and execute the following command to view the contents of your current directory:

```bash
ls
```

This will help ensure you are in the correct directory or locate where the script is stored.

#### **Step 2: Navigate to the Project Directory**

Move to the folder where the script is located. For instance, if your script is stored in the `talk_to_model` folder, navigate to it by running:

```bash
cd /path/to/your/talk_to_model
```

Replace `/path/to/your/talk_to_model` with the actual path to the folder.

#### **Step 3: Execute the Script**

Once in the correct directory, run the following command to start the AI chat interface:

```bash
./talk_to_model.sh
```

This will initiate the script, and you'll be able to interact with the AI directly through your terminal.

### **Option 2: Run via Maven**

If you prefer to run the project through Maven, follow these steps:

#### **Step 1: Navigate to the Project Root Directory**

Ensure you are in the root directory of your Maven project where the `pom.xml` file is located. To check your current directory, run:

```bash
ls
```

If needed, navigate to the correct directory:

```bash
cd /path/to/your/project
```

#### **Step 2: Use Maven to Run the Application**

Execute the following Maven command to run the application:

```bash
mvn spring-boot:run
```

This will compile and execute the project, launching the AI chat terminal interface.

### **Example:**

![Terminal Example](images/terminal_example.png)

This image showcases a typical conversation flow in the terminal.

> Note: Ensure that the script has executable permissions. If needed, you can grant them with the following command:
> ```bash
> chmod +x talk_to_model.sh
> ```

## **Optional: Display System Information**

The script can be enhanced to display system information such as the operating system, disk usage, and memory status alongside the chat interface. This provides added utility by combining system monitoring with AI interaction.

## **Author**

- **Kevin Marville**
- **GitHub:** [kvnbbg](https://github.com/kvnbbg)

## **Acknowledgments**

Special thanks to **Hugging Face** for providing access to their powerful AI models and fostering innovation within the AI community.
