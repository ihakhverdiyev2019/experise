package az.elixir.experise.dto.admin;

public class Payload {

  private boolean success = false;

  private Object result;

  public Payload() {}

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "Payload{" + "success=" + success + ", result=" + result + '}';
  }
}
