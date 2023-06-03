const fs = require('fs');
const readline = require('readline');

let hadError = false;


//TODO: Put all these in a namespace or let's use typescript maybe?
function runFile(path) {
  const bytes = fs.readFileSync(path);
  run(bytes.toString());
  if (hadError) process.exit(65);
}

function runPrompt() {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });

  rl.on('line', (line) => {
    run(line);
    hadError = false;
    console.log('> ');
  });
}

function error(line, message) {
  report(line, '', message);
}

function report(line, where, message) {
  console.error(`[line ${line}] Error${where}: ${message}`);
  hadError = true;
}

function run(source) {
  //TODO: run logic
}

const args = process.argv.slice(2);

if (args.length > 1) {
  console.log('Pilo language:');
  process.exit(64);
} else if (args.length === 1) {
  runFile(args[0]);
} else {
  runPrompt();
}