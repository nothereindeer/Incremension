


class GenGenerator extends Generator{
  
  Generator produce;
  
  GenGenerator(String name, Generator generatorProduced, BigNum baseProduction){
      super(name, baseProduction);
      this.produce = generatorProduced;
    }
  
  void generate(){
    this.produce.amount.add(production);
  }
}