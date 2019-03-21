package de.upb.soot.util.builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Defines the base class for builders.
 *
 * @author Jan Martin Persch
 */
public abstract class AbstractBuilder<T> {
  // region Fields

  // endregion /Fields/

  // region Constructor

  /**
   * Creates a new instance of the {@link AbstractBuilder} class.
   *
   * @param buildableClass The type of the class to build.
   */
  protected AbstractBuilder(@Nonnull Class<T> buildableClass) {
    this._buildableClass = buildableClass;
  }

  // endregion /Constructor/

  // region Properties

  @Nonnull private final Class<T> _buildableClass;

  /**
   * Gets the buildable class.
   *
   * @return The value to get.
   */
  @Nonnull
  public Class<T> getBuildableClass() {
    return this._buildableClass;
  }

  // endregion /Properties/

  // region Methods

  /**
   * Ensures that a value has been set. Use this method for values that must not be <code>null
   * </code>.
   *
   * <p>Example:
   *
   * <pre><code>
   * &#64;Nonnull
   * public ResolvingLevel getResolvingLevel()
   * {
   *    return this.ensureValue(this._resolvingLevel, "resolvingLevel");
   * }
   * </code></pre>
   *
   * @param value The value to ensure.
   * @param name The name of the value
   * @param <V> The type of the value.
   * @return The value.
   * @throws BuilderException The specified value is <code>null</code> that means, the value has not
   *     been set.
   */
  @Nonnull
  protected <V> V ensureValue(@Nullable V value, @Nonnull String name) {
    if (value == null) {
      throw new BuilderException(
          this.getClass(),
          this.getBuildableClass(),
          new IllegalStateException("Value for \"" + name + "\" not set."));
    }

    return value;
  }

  /**
   * Creates an instance of the buildable class.
   *
   * @return The created instance of the buildable class.
   */
  @Nonnull
  protected abstract T make();

  /**
   * Builds the {@link T buildable}.
   *
   * @return The created {@link T buildable}.
   * @throws BuilderException A build error occurred. See the {@link Exception#getCause() cause} to
   *     get details about the original exception.
   */
  @Nonnull
  public T build() {
    try {
      return this.make();
    } catch (RuntimeException e) {
      throw new BuilderException(this.getClass(), this.getBuildableClass(), e);
    }
  }

  @Override
  @Nonnull
  public String toString() {
    return this.getClass().getName() + "<for " + this.getBuildableClass().getName() + ">";
  }

  // endregion /Methods/
}
